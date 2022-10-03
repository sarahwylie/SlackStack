import React from 'react';
import {Card, CardHeader, CardActions, CardContent, Typography, Box} from '@mui/material'

const AnnoucementsCard = (authorName, date, message) =>{
    return(
        <Box sx={{ width: '75%'}}>
            <Card>
                <CardHeader>
                    <h4>{authorName}</h4>
                    <h4>{date}</h4>
                </CardHeader>
                <CardContent>
                    <Typography>
                        {message}
                    </Typography>
                </CardContent>
            </Card>
        </Box>
    );
    
}

export default AnnoucementsCard;