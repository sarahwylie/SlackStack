import React from 'react';
import {useState, useEffect} from 'react';
import { Modal, Paper, Box, Typography, Button, TextField} from '@mui/material'

const BaseModal = (author) =>{

    //State Used for Modal
    const [open, setOpen] = useState(false);
    const handleOpen = () => setOpen(true);
    const handleClose = () => setOpen(false);

    const container = {
        display: "flex",
        flexDirection: "row",
        padding: 35,
        justifyContent: "center",
        alignItems: "center",
        margin: "20% 20%",
        //    background: "rgb(6, 22, 30)"
    };

    return(
        <Modal
            open={open}
            onClose={handleClose}
            aria-labelledby="modal-Announcement-Title"
            aria-describedby="modal-Announcement-Message">
            <Paper style={container}>
                <Box sx ={{ "& .MuiTextField-root": { m: 2, width: "25ch" }}} style={container}>
                    <Button onClick={handleClose}></Button>
                    <Typography id="modal-Announcement-Title">
                        <h3>{author}</h3>
                    </Typography>
                    <Typography id="modal-Announcement-Message">
                        <TextField
                            required
                            id="outlined-required"
                            label="Announcement"
                        />
                        <Button>Submit</Button>
                    </Typography>
                </Box>
            </Paper>
        </Modal>
    )

}