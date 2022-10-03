import * as React from "react";

import { useNavigate, Link } from "react-router-dom";

import { useState } from "react";
import AppBar from "@mui/material/AppBar";
import Box from "@mui/material/Box";
import Toolbar from "@mui/material/Toolbar";
// import Typography from "@mui/material/Typography";
// import Button from "@mui/material/Button";

import Menu from "@mui/material/Menu";
import IconButton from "@mui/material/IconButton";
import MenuIcon from "@mui/icons-material/Menu";
import logo from '../Assets/logo.png'
import { MenuItem } from "@mui/material";
import {nav, menuIcon, menuModal, menu} from './component-Styles/mui-stylez'

const NavBar = () => {
  let userData = localStorage.getItem("userData");
  let user = JSON.parse(userData);
// eslint-disable-next-line
  const navigate = useNavigate();



  const [anchorEl, setAnchorEl] = useState(null);
  const open = Boolean(anchorEl);
  const handleClick = e => {
    setAnchorEl(e.currentTarget);



  };
  const handleClose = e => {
    setAnchorEl(null);
  };


  const handleLogout = () => {
    navigate("/")
    localStorage.removeItem("userData")
    localStorage.removeItem("admin")
    localStorage.removeItem("company")
  }


  return (
    <Box  sx={{flexGrow: 1}}>
      <AppBar style={nav} position="static">
        <Toolbar sx={{ justifyContent: "space-between" }}>
        <img src={logo} className="img2" alt="files"/>
        {user.credentials.admin ? (<h2 style={{ color: "rgb(255, 0, 0)", marginRight: "65%"}}>Acting as Admin</h2>) : (<></>)}
          <IconButton
              size="large"
              style={menuIcon}
              id="demo-positioned-button"
              aria-controls={open ? "demo-positioned-menu" : undefined}
              aria-haspopup="true"
              aria-expanded={open ? "true" : undefined}
              onClick={handleClick}>
            <MenuIcon />
          </IconButton>


          <Menu

            id="demo-positioned-menu"
            aria-labelledby="demo-positioned-button"
            anchorEl={anchorEl}
            open={open}
            onClose={handleClose}
            anchorOrigin={{
              vertical: "top",
              horizontal: "left",
            }}
            transformOrigin={{
              vertical: "top",
              horizontal: "left",
            }}
          >


            {user.credentials.admin ? (
              <div style={menuModal}>
                <MenuItem>
                  <Link to="/" style={menu}>Home</Link>
                </MenuItem>
                <MenuItem>
                  <Link to="/teamoverview" style={menu}>Teams</Link>
                </MenuItem>
                <MenuItem>
                  <Link to="/users" style={menu}>Users</Link>
                </MenuItem>
                <MenuItem>
                  <Link to="/projects" style={menu}>Projects</Link>
                </MenuItem>
                <MenuItem onClick={handleLogout}>
                  Logout
                </MenuItem>
              </div>
            ) : (
              <div style={menuModal}>
                <MenuItem>
                  <Link to="/" style={menu}>Home</Link>
                </MenuItem>
                <MenuItem>
                  <Link to="/projects" style={menu}>Projects</Link>
                </MenuItem>
                
                <MenuItem onClick={handleLogout}>
                  Logout
                </MenuItem>
              </div>
            )}
          </Menu>


        </Toolbar>
      </AppBar>
    </Box>
  );
};



export default NavBar;

