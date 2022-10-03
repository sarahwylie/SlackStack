import React, { useEffect, useState } from "react";
import TextField from "@mui/material/TextField";
import { Box, Button, Paper, Card, Modal, Grid, Typography, Tooltip } from "@mui/material";
// import styled from "@emotion/styled";
// import { useNavigate } from "react-router-dom";
// import { width } from "@mui/system";
import NavBar from "./NavBar";
import fetchFromCompany from "../services/api";
import { container, card, modal, input, ex } from './component-Styles/mui-stylez'
import HighlightOffIcon from '@mui/icons-material/HighlightOff';

const Announcements = () => {
  //userData will need to be set in the app.js then passed to the components that need it.
  let userData = localStorage.getItem("userData")
  let user = JSON.parse(userData)

  console.log('from announcements', user)


  const [announcementsToSet, setAnnouncementsToSet] = useState();
  const [announcementToCreate, setAnnouncementToCreate] = useState("");
  const [modalOpen, setModalOpen] = useState(false);

  useEffect(() => {
    const getAnnouncements = () => {
      let company = localStorage.getItem("company")
      console.log("from get announcmements", company)
      // eslint-disable-next-line
      const response = fetchFromCompany({
        endpoint: `companies/${company}/announcements`,

      }).then((data) => {
        console.log(data)
        setAnnouncementsToSet(data)
      })

    }
    getAnnouncements()
  }, [])


  const handleNewAnnouncement = () => {
    let company = localStorage.getItem('company')
    fetchFromCompany({
      method: 'POST',
      endpoint: `companies/${company}/users/${user.id}/announcements`,
      body: {
        title: 'New announcement',
        message: announcementToCreate,
        userId: userData.id,
        companyId: company
      }
    }).then(newAnnouncement => {
      setAnnouncementsToSet([...announcementsToSet, newAnnouncement])
      setAnnouncementToCreate('')
      setModalOpen(false)
    })
  }

  return announcementsToSet ? (
    <>
      <NavBar />
      <Paper style={container}>
        <Typography style={{ margin: "20px 0" }} variant="h3" component="h1">
          Announcements
        </Typography>
        {user.credentials.admin ? (
          <div style={{ justifyContent: "flex-end" }}><Button
            onClick={() => setModalOpen(true)}
            variant="contained"
            size="small"
            style={{ backgroundColor: "teal", color: "white", marginLeft: "500%" }}
          >
            New
          </Button></div>
        ) : null}
        _________________________________________________________________________________________________
        <br />
        {announcementsToSet.map((announcement, idx) =>
        (<Grid container sx={{ flexDirection: "row", margin: 0, justifyContent: "space-around" }}>
          <Card style={card} xs={10}
            sx={{
              borderRadius: 6,
              background: "#0C2D48",
              // padding: "10%",
              marginTop: 3,
              // height: "20%",
              width: "75%"
            }}
            key={idx}>
            <h3 style={{ color: "#fff", marginRight: "60%", marginBottom: 0 }}>{user.firstName}</h3>
            <h4 style={{ color: "#fff", marginLeft: "60%", marginTop: ".5em" }}>30 September 2022</h4>
            <h2>{announcement.title}</h2>
            <p style={{ padding: "1%", paddingTop: 0 }}>{announcement.message}</p>
          </Card>
        </Grid>
        )
        )}


        <Modal open={modalOpen} style={card}
          onClose={() => setModalOpen(false)}>
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
              value={announcementToCreate}
              onChange={e => setAnnouncementToCreate(e.target.value)}
              size="small"
              id="standard-required"
              variant="standard"
              required
              label="Announcement"
              style={input}
            />
            <div style={{ textAlign: "center", marginTop: 20 }}>
              <Button
                variant="contained"
                size="small"
                style={{ backgroundColor: "teal", color: "white", marginTop: 20, marginRight: 0 }}
                onClick={handleNewAnnouncement}
              >
                {" "}
                Submit
              </Button>
            </div>
          </Box>
        </Modal>
      </Paper>
    </>
  ) : <h1>Loading...</h1>;
};

export default Announcements;
