import React, { useState, useEffect } from "react";
import {
  Box,
  Modal,
  Button,
  Typography,
  TextField,
  Select,
  MenuItem,
  Tooltip,
  Card,
  Paper,
  FormControl,
  InputLabel,
  // Input,
  Grid
} from "@mui/material";
// import MultipleSelect from 'material-ui-multiple-select'
import HighlightOffIcon from '@mui/icons-material/HighlightOff';
import NavBar from "./NavBar"
import fetchFromCompany from "../services/api";
import "../components/component-Styles/main.css";
import { container, card, modal, input, ex, select } from './component-Styles/mui-stylez';

const TeamOverview = () => {
  const [modalOpen, setModalOpen] = useState(false);
  const [teamName, setTeamName] = useState("");
  const [description, setDescription] = useState("");
  const [users, setUsers] = useState([]);
  const [teams, setTeams] = useState([]);
  // const [userIds, setUserIds] = useState()
  // const [newTeamId, setNewTeamId] = useState()
  // const [membersToAdd, setMembersToAdd] = useState([])
  const [membersToAdd, setMembersToAdd] = useState([]);
  console.log("memberstoadd>>>>", membersToAdd)
  // let company = localStorage.getItem("company");
  let userData = localStorage.getItem("userData");
  let user = JSON.parse(userData);

  const getUsers = async () => {
    const response = await fetchFromCompany({
      endpoint: `users`,
    });
    setUsers(response);
    return response;
  };

  const getTeams = async () => {
    let company = localStorage.getItem("company");
    const response = await fetchFromCompany({
      endpoint: `companies/${company}/teams`,
    });
    setTeams(response);
    return response;
  };

  const addNewMembers = async (teamId, username) => {
    console.log("addingnew members")
    const response = await fetchFromCompany({
      method: "PATCH",
      endpoint: `users/${username}/${teamId}`
    })

    getTeams()
  }

  const createTeam = async () => {
    let company = localStorage.getItem("company");

    console.log(">>>>", company)
    const response = await fetchFromCompany({
      method: "POST",
      endpoint: `companies/${company}/teams`,
      body: {
        name: teamName,
        description: description,
      },
    })
    setMembersToAdd([])
    for (let i = 0; i < membersToAdd.length; i++) {
      addNewMembers(response.id, membersToAdd[i])
    }
    setTeamName('')
    setDescription('')
    setModalOpen(false);
    getTeams()

    console.log("New team", response)
  }

  useEffect(() => {
    getUsers();
  }, [teams]);

  useEffect(() => {
    getTeams()
  }, [])

  const handleChange = e => {
    setMembersToAdd(e.target.value);
  }

  const members = users.filter(user => user.team == null);
  console.log("these are memebers", members)

  return teams ? (
    <>
      <NavBar />
      <Paper style={container}
        sx={{ width: "100%", marginTop: 0 }}>
        <Typography style={{ margin: "20px 0" }} variant="h3" component="h1">
          Teams
        </Typography>
        <Grid container sx={{ flexDirection: "row", margin: 0, justifyContent: "space-around", flexWrap: 'wrap', width: "100%" }}>
          {user.credentials.admin ? (
            <Button xs={2} onClick={() => setModalOpen(true)}
              variant="outlined"
              style={{
                border: '3px solid #DEB992',
                borderRadius: 20,
                width: "20%",
                height: "0%",
                padding: "8%",
                // margin: "5% 5%",
                color: '#DEB992',
                fontSize: 25
              }}
            >+<br />New Team</Button>
          ) : null}
          {teams.map((team, idx) => (
            <Card style={card} xs={2}
              sx={{
                borderRadius: 6,
                background: "#0C2D48",
                width: "18%",
                height: "35%",
                padding: "3%"
              }} team={team} >
              <div style={{ display: "flex", flexDirection: "row", borderBottom: '2px solid #DEB992' }}>
                <h2 style={{ color: "#fff", margin: 0, justifyContent: "flex-start" }}>{team.name}</h2>
                <h3 style={{ color: "#fff", marginBottom: "2%", justifyContent: "flex-end" }}># of Projects: 5</h3>
              </div>
              <div>
                <h3 style={{ color: "#fff" }}>Members</h3>
                <div style={{ display: "flex", flexDirection: "row", justifyContent: "space-around" }}>
                  {team.users.map((user) => (
                    <Button variant="contained"
                      size="small"
                      style={{ backgroundColor: "teal", color: "white", margin: "5%", boxShadow: "3px 3px 3px black" }}>
                      {user.firstName}
                    </Button>
                  ))}
                </div>
              </div>
            </Card>
          ))}
        </Grid>
        <Modal open={modalOpen} onClose={() => modalOpen(false)} style={card}>
          <Box component="form" style={modal} sx={{
            boxShadow: "2px 2px 2px",
            borderRadius: 6,
            padding: "10%",
          }}>
            <Tooltip title="Close"><HighlightOffIcon
              onClick={() => setModalOpen(false)}
              style={ex}
            /></Tooltip>
            <TextField
              value={teamName}
              onChange={e => setTeamName(e.target.value)}
              size="small"
              required
              label="team name"
              id="standard-required"
              variant="standard"
              style={input}
            />
            <TextField
              value={description}
              onChange={e => setDescription(e.target.value)}
              size="small"
              required
              id="standard-required"
              variant="standard"
              style={input}
              label="description"
            />
            <div style={{ textAlign: "center", marginTop: "20px" }}>
              <FormControl fullWidth>
                <InputLabel id="demo-simple-select-label">Select Members</InputLabel>
                {/* <InputLabel htmlFor="multi"><Typography component="h6">Select Members</Typography></InputLabel> */}
                <Select
                  // multiple
                  labelId="members-to-select"
                  value={membersToAdd}
                  style={select}
                  label="Pick an option"
                  onChange={handleChange}
                >
                  {members.map(option => (
                    <MenuItem key={option.id} value={option.credentials.username}>
                      {option.firstName}
                    </MenuItem>
                  ))}
                </Select>
              </FormControl>
            </div>
            <Button
              variant="contained"
              size="small"
              style={{ backgroundColor: "teal", color: "white", marginTop: 20, marginRight: 0 }}
              onClick={createTeam}
            >
              Submit
            </Button>
          </Box>
        </Modal>
      </Paper>
    </>
  ) : null;
};

export default TeamOverview;