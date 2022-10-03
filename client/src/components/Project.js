import React from "react";
// import NavBar from "./NavBar";
import { Card, Button } from "@mui/material";

const Project = ({ project, handleClick }) => {
  // const millisecondsInDay = 86400000;
  // const elapsedDays = Math.floor((Date.now() - project["last-edited"]) / millisecondsInDay);

  return (
    <Card
      style={{ color: "white", fontFamily: "helvetica" }}
      sx={{ boxShadow: 0, borderRadius: 0 }}
    >
      <div style={{ display: "flex", flexDirection: "row", alignItems: "flex-end", textAlign: "left", backgroundColor: "rgb(6, 22, 30)"}}>
        <div style={{ width: "80%", margin: "20px 0" }}>
          <p style={{ fontSize: 24, fontWeight: "bold", lineHeight: 0 }}>
            {project.name}{" "}
            {/* <span style={{ fontSize: 13 }}>
              Last edited {elapsedDays} day{elapsedDays === 1 ? "" : "s"} ago
            </span> */}
          </p>
          <p style={{ fontSize: 13 }}>{project.description}</p>
        </div>

        <div
          style={{
            width: "20%",
            display: "flex",
            flexDirection: "row",
            justifyContent: "flex-end",
            paddingBottom: 20,
          }}
        >
          <Button
            style={{
              textTransform: "none",
              fontSize: 13,
              width: "80px",
              height: "25px",
              borderRadius: 8,
              backgroundColor: "#DEB992",
            }}
            onClick={() => handleClick(project.id)}
          >
            Edit
          </Button>
        </div>
      </div>
      <hr />
    </Card>
  );
};

export default Project;
