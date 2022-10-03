import React, { useState, useEffect } from "react";
import NavBar from "./NavBar";
import {
  Table,
  TableBody,
  TableCell,
  TableContainer,
  TableHead,
  TableRow,
  Paper,
  Link,
  Button,
  Typography,
  Modal,
  Box,
  TextField,
  Select,
  MenuItem,
  CircularProgress,
  Tooltip
} from "@mui/material";
import fetchFromCompany from "../services/api";
import { table, card, modal, input, ex } from './component-Styles/mui-stylez';
import HighlightOffIcon from '@mui/icons-material/HighlightOff';

const emptyUserObject = {
  firstName: "",
  lastName: "",
  credentials: {
    username: "",
    password: "",
    admin: false,
  },
  status: "Unknown",
  email: "",
  phoneNumber: "0000000000",
  team: {
    id: 0,
    name: "",
    description: "",
  },
  company: {
    id: 0,
    name: "",
    description: "",
  },
};

const Users = props => {
  const [users, setUsers] = useState(null);
  const [newUser, setNewUser] = useState(emptyUserObject);
  const [isModalOpen, setIsModalOpen] = useState(false);
  const [passwordCheck, setPasswordCheck] = useState("");
  const [isValidated, setIsValidated] = useState(false);
  //
  let user = JSON.parse(localStorage.getItem("userData"));

  useEffect(() => {
    fetch("http://localhost:8080/users")
      .then(response => response.json())
      .then(data => setUsers(data));
  }, []);

  useEffect(() => {
    if (validateForm()) setIsValidated(true);
    else setIsValidated(false);
    // eslint-disable-next-line
  }, [newUser, passwordCheck]);

  const handleSubmit = e => {
    console.log("working")
    console.log(newUser);
    e.preventDefault();
    setNewUser({
      ...newUser,
      credentials: {
        username: newUser.firstName + newUser.lastName,
        admin: newUser.credentials.admin,
        password: newUser.credentials.password,
      },
    });
    postNewUser();
    setPasswordCheck("");
    setIsModalOpen(false);
    setIsValidated(false);
  };

  const handleChange = e => {
    if (e.target.name === "password") {
      setNewUser({
        ...newUser,
        credentials: {
          username: newUser.firstName + newUser.lastName,
          admin: newUser.credentials.admin,
          password: e.target.value,
        },
      });
    } else if (e.target.name === "admin") {
      setNewUser({
        ...newUser,
        credentials: {
          username: newUser.firstName + newUser.lastName,
          password: newUser.credentials.password,
          admin: e.target.value,
        },
      });
    } else {
      setNewUser({ ...newUser, [e.target.name]: e.target.value });
    }
  };

  const cancelSubmit = newUser => {
    setNewUser(emptyUserObject);
    setPasswordCheck("");
    setIsModalOpen(false);
    setIsValidated(false);
  };

  const validateForm = () => {
    return (
      newUser.firstName.trim() &&
      newUser.lastName.trim() &&
      newUser.email.trim() &&
      newUser.credentials.password.trim() &&
      newUser.credentials.password.trim() === passwordCheck
    );
  };

  const postNewUser = async () => {
    console.log("posting new user")
    const returnedUser = await fetchFromCompany({
      method: "POST",
      endpoint: "users",
      body: newUser,
    }).then(newUser => {
      setUsers([...users, newUser])
      setNewUser(emptyUserObject);
    })

    // window.location.reload(false);
  };

  return (
    <div>
      <NavBar />
      {users ? (
        <div style={{ textAlign: "center", width: "80%", margin: "0 auto" }}>
          <Typography style={{ margin: "20px 0" }} variant="h3" component="h1">
            User Registry
          </Typography>
          <Typography style={{ marginBottom: 20, color: "#1BA098" }} component="p">
            A general view of all your members in your organization.
          </Typography>
          <TableContainer component={Paper} elevation={4}
            style={table}>
            <Table>
              <TableHead>
                <TableRow>
                  <TableCell style={{ fontSize: 16, fontWeight: "bold" }} align="center">
                    Name
                  </TableCell>
                  <TableCell style={{ fontSize: 16, fontWeight: "bold" }} align="center">
                    Email
                  </TableCell>
                  <TableCell style={{ fontSize: 16, fontWeight: "bold" }} align="center">
                    Team
                  </TableCell>
                  <TableCell style={{ fontSize: 16, fontWeight: "bold" }} align="center">
                    Active
                  </TableCell>
                  <TableCell style={{ fontSize: 16, fontWeight: "bold" }} align="center">
                    Admin
                  </TableCell>
                  <TableCell style={{ fontSize: 16, fontWeight: "bold" }} align="center">
                    Status
                  </TableCell>
                </TableRow>
              </TableHead>
              <TableBody>
                {users.map(newUser => (
                  <TableRow key={newUser.id} sx={{ color: "DEB992", borderTop: '2px solid #DEB992' }}>
                    <TableCell style={{ color: "#DEB992" }} component="th" scope="row" align="center">
                      {newUser.firstName + " " + newUser.lastName}
                    </TableCell>
                    <TableCell style={{ color: "#DEB992" }} align="center">
                      <Link style={{ textDecoration: 'none' }} color="inherit" href={`mailto:${newUser.email}`}>{newUser.email}</Link>
                    </TableCell>
                    <TableCell style={{ color: "#DEB992" }} align="center">{newUser.team?.name || "-"}</TableCell>
                    <TableCell
                      align="center"
                      style={{
                        fontWeight: "bold",
                        color: newUser.active ? "green" : "red",
                      }}
                    >
                      {newUser.active ? "YES" : "NO"}
                    </TableCell>
                    <TableCell
                      align="center"
                      style={{
                        fontWeight: "bold",
                        color: newUser.credentials.admin ? "green" : "red",
                      }}
                    >
                      {newUser.credentials.admin ? "YES" : "NO"}
                    </TableCell>
                    <TableCell style={{ color: "#DEB992" }} align="center">{newUser.status}</TableCell>
                  </TableRow>
                ))}
              </TableBody>
            </Table>
          </TableContainer>
          <div style={{ textAlign: "left" }}>
            {user.credentials.admin ? <Button
              onClick={() => setIsModalOpen(true)}
              variant="contained"
              size="small"
              style={{ backgroundColor: "teal", color: "white", marginTop: 20 }}
            >
              Add User
            </Button> : null}
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
                value={newUser.firstName}
                onChange={handleChange}
                size="small"
                required
                id="standard-required"
                variant="standard"
                label="First Name"
                name="firstName"
                style={input}
              />
              <TextField
                value={newUser.lastName}
                onChange={handleChange}
                name="lastName"
                size="small"
                required
                id="standard-required"
                variant="standard"
                label="Last Name"
                style={input}
              />
              <TextField
                value={newUser.email}
                type="email"
                onChange={handleChange}
                fullWidth
                size="small"
                required
                id="standard-required"
                variant="standard"
                label="Email"
                name="email"
                style={input}
              />
              <TextField
                value={newUser.credentials.password}
                onChange={handleChange}
                size="small"
                required
                id="standard-required"
                variant="standard"
                type="password"
                label="Password"
                name="password"
                style={input}
              />
              <TextField
                value={passwordCheck}
                onChange={e => setPasswordCheck(e.target.value)}
                size="small"
                required
                id="standard-required"
                variant="standard"
                style={input}
                type="password"
                label="Confirm Password"
                error={newUser.credentials.password !== passwordCheck}
              />
              <div style={{ textAlign: "center", marginTop: "20px" }}>
                <Typography component="h6">Make User Admin?</Typography>
                <Select
                  size="small"
                  value={newUser.credentials.admin}
                  onChange={handleChange}
                  label="Pick an option"
                  name="admin"
                >
                  <MenuItem value={true}>True</MenuItem>
                  <MenuItem value={false}>False</MenuItem>
                </Select>
              </div>
              <div style={{ textAlign: "center", marginTop: 20 }}>
                <Button
                  variant="contained"
                  size="small"
                  style={{ backgroundColor: "teal", color: "white", marginTop: 20, marginRight: 0 }}
                  disabled={!isValidated}
                  onClick={handleSubmit}
                >
                  Submit
                </Button>
              </div>
            </Box>
          </Modal>
        </div>
      ) : (
        <CircularProgress />
      )}
    </div>
  );
};

export default Users;