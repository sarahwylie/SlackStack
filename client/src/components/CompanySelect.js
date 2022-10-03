import React, { useEffect, useState } from "react";
import {
  Box,
  Paper,
  FormControl,
  InputLabel,
  Select,
  MenuItem,
} from "@mui/material";
import { container, card, select } from './component-Styles/mui-stylez'
// eslint-disable-next-line
import fetchFromCompany, { request } from "../services/api";
import { useNavigate } from "react-router-dom";


const CompanySelect = ({ userData, setCompany }) => {
  console.log("company select", userData)
  const navigate = useNavigate();

  const [companies, setCompanies] = useState()

  const getCompanies = async () => {
    // eslint-disable-next-line
    const response = await fetchFromCompany({
      endpoint: "companies",

    }).then((data) => {

      setCompanies(data)

    })
  }

  useEffect(() => {
    getCompanies()
  }, [])

  // handle setting company for admin and sending them to announcments page for their company
  const handleChange = event => {
    localStorage.removeItem("company")
    localStorage.setItem("company", event.target.value);
    navigate("/announcements");
  };

  return (
    <Paper style={container}>
      {companies ?
        <Box component="form" noValidate autoComplete="off" style={card}>
          <h1>Select Company</h1>
          <FormControl fullWidth>
            <InputLabel id="demo-simple-select-label">Company</InputLabel>
            <Select
              labelId="companies-to-select"
              id="cpmpanies"
              value={''}
              label="Pick an option"
              style={select}
              onChange={handleChange}
            >
              {companies.map(company => (
                <MenuItem value={company.id}>{company.name}</MenuItem>
              ))}
            </Select>
          </FormControl>
        </Box>
        : null}
    </Paper>)
};

export default CompanySelect;