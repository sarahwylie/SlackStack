
import React, { useState } from "react";
import {
  // BrowserRouter as Router,
  Routes,
  Route,
  useNavigate,
  // Navigate,
} from "react-router-dom";
import { ThemeProvider } from '@emotion/react';
import {theme, container} from './component-Styles/mui-stylez'
import {Paper} from "@mui/material";
// eslint-disable-next-line
import fetchFromCompany, { request } from "../services/api";

import CompanySelect from "./CompanySelect";

import Login from "./Login";
import Announcements from "./Announcements";
import TeamOverview from "./TeamOverview";
import Projects from "./Projects";
import Users from "./Users";


//CSS import
import "../components/component-Styles/main.css";

const App = () => {
  //username will be set here so that it can be passed to other components as well as Company for admin
  const [userName, setUserName] = useState();
  const [password, setPassword] = useState();
  const [userData, setUserData] = useState();
  const [company, setCompany] = useState();
//  console.log("from app", userData)
  
  let navigate = useNavigate();


  const loginAuth = async () => {
    try {
      const response = await fetchFromCompany({
        endpoint: "auth/login",
        method: "POST",
        body: {
          username: userName,
          password: password,
        },
      })
      setUserData(response)
      localStorage.setItem("userData", JSON.stringify(userData))
      //this seems to be trying to read credentials before it's there. That's why you have to clcik twice
      if(userData){
        userData.credentials.admin
        ? navigate("/company")
        : navigate("/announcements");
      }
      return response
    } catch (error) {
      console.log('from error',error.message)
      
    }
    
   
  }

  const handleLogin = () => {
    loginAuth()
   
  };

  return (
      <React.Fragment>
      <ThemeProvider theme={theme}>
      <Paper style={container}>
      <Routes>
      <Route
        path="/"
        element={<Login handleLogin={handleLogin} setUserName={setUserName} setPassword={setPassword} />}
      />
      <Route path="/company" element={<CompanySelect userData={userData} setCompany={setCompany} />} />
      <Route path="/TeamOverview" element={<TeamOverview />} />

      <Route
        path="/announcements"
        element={<Announcements userData={userData} company={company}/>}
      />

      <Route path="/projects" element={<Projects />} />
      <Route path="/users" element={<Users />} />
      </Routes>
      </Paper>
      </ThemeProvider>
      </React.Fragment>
  );
};

export default App;
