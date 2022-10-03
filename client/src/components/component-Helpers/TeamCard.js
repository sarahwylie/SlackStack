import React from "react";
import Button from "react-bootstrap/Button";
import Stack from "react-bootstrap/Stack";
import { Link } from "react-router-dom";

const TeamCard = ({team}) => {
  return team ? (
    <div class="card">
      <div class="card-header">
        <Stack direction="horizontal" gap={2}>
          <h2>{team.name}</h2>
          <h3># of Projects: 5</h3>
        </Stack>
      </div>
      ___________________________________________________________________________
      <div class="card-body">
        <h3>Members</h3>
        {team.users.map((user) => (
        <Button>
          <Link >{user.firstName}</Link>
        </Button>
        ))}
      </div>
    </div>
  ) : null;
};

export default TeamCard;
