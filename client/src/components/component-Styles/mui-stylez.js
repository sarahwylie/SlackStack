import { styled } from "@mui/material/styles";
import MuiButton from "@mui/material/Button";
import { createTheme } from '@mui/material';

export const theme = createTheme({
    palette: {
        mode: 'dark',
        primary: {
            main: "rgb(6, 22, 30)",
            constrastText: "#fff"
        },
        secondary: {
            main: "#1BA098"
        },
        success: {
            main: "#DEB992"
        },
        info: {
            main: "#0F5583"
        }
    },
});

export const Button = styled(MuiButton)(({ pill }) => ({
    color: "#fff",
    borderRadius: pill ? 50 : 4,
    border: '2px solid',
    borderColor: "#1BA098",
    marginTop: '10%',
    padding: '.5em 2.5em'
}));

export const container = {
    display: "flex",
    flexDirection: "column",
    // padding: 45,
    justifyContent: "center",
    alignItems: "center",
    background: "rgb(6, 22, 30)",
    width: "100vhw",
    height: "100vh",
};

export const card = {
    // border: '5px solid #DEB992',
    // boxShadow: "8px 8px 8px #DEB992",
    // borderRadius: 6,
    display: "flex",
    flexDirection: "column",
    // padding: 2,
    justifyContent: "center",
    alignItems: "center",
    // margin: "5% 5%",
    textAlign: "center",
    wordWrap: 'break-word'
}

export const input = {
    borderBottom: "white solid",
    color: "#1BA098",
    margin: "2%",
    // background: "rgb(6, 22, 30)",
};

export const select = {
    width: "400px",
    // background: "#FFFFFF",
    // color: "#1BA098"
};

export const nav = {
    marginTop: 0,
    marginRight: 0,
    marginLeft: 0,
    marginBottom: "2%",
    width: "100%",
    background: "rgb(6, 22, 30)",
    border: '2px solid #DEB992',
}

export const menuIcon = {
    background: "#0C2D48",
    borderRadius: 6,
}

export const modal = {
    background: "#0C2D48",
    color: "#fff",
    position: "absolute",
    top: "50%",
    left: "50%",
    transform: "translate(-50%, -50%)",
    // width: 400,
    boxShadow: 24,
    padding: "3%"
}

export const table = {
    display: "flex",
    flexDirection: "column",
    padding: 2,
    justifyContent: "center",
    alignItems: "center",
    textAlign: "center",
    wordWrap: 'break-word',
    border: '2px solid #DEB992',
    borderRadius: 6,
    background: "rgb(6, 22, 30)",
    align: "center"
}

export const menuModal = {
    background: "#0C2D48",
    color: "#1BA098",
    borderRadius: 6,
}

export const menu = {
    borderBottom: '2px solid #1BA098',
    textDecoration: 'none',
    color: "#1BA098"
}

export const ex = {
    color: "rgb(255, 0, 0)", 
    marginLeft: "80%", 
    marginBottom: "10%",
    cursor: "pointer"
}