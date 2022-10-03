import React, { useState, useEffect } from "react";
import Project from "./Project";
import {
  Typography,
  Button,
  Container,
  Modal,
  TextField,
  Box,
  Tooltip
} from "@mui/material";
import fetchFromCompany from "../services/api";
import NavBar from "./NavBar";
import { card, input, modal, ex } from './component-Styles/mui-stylez';
import HighlightOffIcon from '@mui/icons-material/HighlightOff';

const emptyProjectObject = {
  id: null,
  name: "",
  description: "",
  active: false,
  team: {
    id: null,
  },
};

const Projects = props => {
  const [isModalOpen, setIsModalOpen] = useState(false);
  const [projects, setProjects] = useState([]);
  const [project, setProject] = useState(emptyProjectObject);
  const [isValidated, setIsValidated] = useState(false);

  let user = JSON.parse(localStorage.getItem("userData"));

  // GET projects from company ID /companies/{id}/projects
  useEffect(() => {
    fetch(`http://localhost:8080/companies/${user.company.id}/projects`)
      .then(response => response.json())
      .then(data => setProjects(data))
      .catch(err => console.error(err));
  }, [user]);

  useEffect(() => {
    if (validateForm()) setIsValidated(true);
    else setIsValidated(false);
  }, [project]);


  const handleChange = e => {
    setProject({
      ...project,
      [e.target.name]: e.target.value,
    });
  };

  const editProject = projectId => {
    const { id, name, description } = projects.find(p => p.id === projectId);
    setProject({ id, name, description });
    setIsModalOpen(true);
  };

  const handleSubmitProject = e => {
    e.preventDefault();
    if (project.id) {
      // PATCH existing project /companies/{companyId}/teams/{teamId}/projects/{projectId}
      patchProject();
    } else {
      // POST new project /companies/{companyId}/teams/{teamId}/projects
      postProject();
    }
    window.location.reload(false);
    setProject(emptyProjectObject);
    setIsModalOpen(false);
    setIsValidated(false);
  };

  const postProject = async () => {
    // eslint-disable-next-line
    const returnedProject = await fetchFromCompany({
      method: "POST",
      endpoint: `companies/${user.company.id}/teams/${user.team.id}/projects`,
      body: project,
    }).then(newProject => {
      setProjects([...projects, newProject])
      setProject(emptyProjectObject);
    })

  };

  const patchProject = async () => {

    const returnedProject = await fetchFromCompany({
      method: "PATCH",
      endpoint: `companies/${user.company.id}/teams/${user.team.id}/projects/${project.id}`,
      body: project,
    });
    console.log("Patched Project: ", returnedProject);
    setProject(emptyProjectObject);
    window.location.reload(false);
  };

  const cancelSubmit = () => {
    setIsModalOpen(false);
    setProject(emptyProjectObject);
  };

  const validateForm = () => {
    return project.name.trim() && project.description.trim();
  };

  return (
    <div>
      <NavBar />
      <Container sx={{ width: "80%", textAlign: "center" }}>
        <Typography style={{ margin: "20px 0", color: "#1BA098" }} variant="h3" component="h1">
          {user ? user.team.name : "Team"} Projects
        </Typography>

        <div style={{ textAlign: "right" }}>
          {user.credentials.admin ? <Button
            style={{
              textTransform: "none",
              fontSize: 13,
              width: "80px",
              height: "25px",
              backgroundColor: "teal",
              color: "white",
              borderRadius: 8,
              marginBottom: 20,
            }}
            onClick={() => setIsModalOpen(true)}
          >
            New
          </Button> : null}
        </div>
        <div>
          <hr />
          {projects.map(p =>
            p.team.id === user.team.id && p.active ? (
              <Project key={p.id} project={p} isAdmin={false} handleClick={editProject} />
            ) : null
          )}
        </div>
        <Modal open={isModalOpen} onClose={() => setIsModalOpen(false)} style={card}>
          <Box component="form" style={modal} sx={{
            boxShadow: "2px 2px 2px",
            borderRadius: 6,
            padding: "10%",
          }}>
              <Tooltip title="Close"><HighlightOffIcon
                onClick={cancelSubmit}
                style={ex}
              /></Tooltip>
            <TextField
              value={project.name}
              onChange={handleChange}
              size="small"
              required
              id="standard-required"
              variant="standard"
              name="name"
              label="Project Name"
              fullWidth
              style={input}
            />
            <TextField
              value={project.description}
              onChange={handleChange}
              size="small"
              required
              id="standard-required"
              variant="standard"
              name="description"
              label="description"
              fullWidth
              style={input}
            />
            <div style={{ textAlign: "center", marginTop: 20 }}>
              <Button
                variant="contained"
                size="small"
                style={{ backgroundColor: "teal", color: "white", marginTop: 20, marginRight: 0 }}
                disabled={!isValidated}
                onClick={handleSubmitProject}
              >
                Submit
              </Button>
            </div>
          </Box>
        </Modal>
      </Container>
    </div>

  );
};

export default Projects;
