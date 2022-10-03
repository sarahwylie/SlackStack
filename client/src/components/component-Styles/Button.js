import { styled } from "@mui/material/styles";
import MuiButton from "@mui/material/Button";

export const Button = styled(MuiButton)(({ pill }) => ({
    borderRadius: pill ? 50 : 4,
    border: '2px solid'
  }));


  .container {
    display: flex;
    flex-direction: column;
    /* padding: 3em; */
    justify-content: center;
    align-items: center;
    margin: 10%;
}

.card {
    flex-direction: row;
    border-color: #DEB992;
    display: flex;
    color: #1BA098BF;
    font-size: large;
    font-weight: bold;
    border-radius: 3%;
    border-width: 10px;
    padding: 2em;
    height: 100%;
    width: 100%;
}

Button {
    border-radius: 15%;
    border-width: .5em;
    border-color: #1BA098BF;
    color: white;
} 