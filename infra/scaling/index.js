const express = require('express');
const { ServicesClient } = require('@google-cloud/run').v2;

const app = express();
app.use(express.json());

const PROJECT_ID = 'tinypet-404519'
const REGION = 'europe-west1';
const SERVICE_ID = 'linkedout';


app.post('/', async (req, res) => {
    const message = req.body.message;
    console.log(`Received message: ${message}`);
    let minInstanceCount;

    if (message === 'scale-zero')
        minInstanceCount = 0;
    else if (message === 'scale-one')
        minInstanceCount = 1;
    else return res.status(400).send('Unknown message');

    try {
        const runClient = new ServicesClient();
        const [service] = await getService(runClient);
        const response = await scaleTo(runClient, service, minInstanceCount);

        res.status(200).send(response);
    } catch (error) {
        console.error(`Error scaling service: ${error}`);
        res.status(500).send('Error scaling service');
    }
});

app.get('/', async (req, res) => {
    try {
        const runClient = new ServicesClient();
        const [service] = await getService(runClient);
        res.status(200).send(service);
    } catch (error) {
        console.error(`Error getting service: ${error}`);
        res.status(500).send('Error getting service');
    }
});


const port = process.env.PORT || 8080;
app.listen(port, () => {
    console.log(`Listening on port ${port}`);
});


async function getService(runClient) {
    const service = `projects/${PROJECT_ID}/locations/${REGION}/services/${SERVICE_ID}`;
    const request = {
        name: service,
    };

    return await runClient.getService(request);
}

async function scaleTo(runClient, service, instanceCount){
    let updateMask = {
        paths: ['scaling.minInstanceCount']
    };
    service.scaling = {
        minInstanceCount: instanceCount
    }
    const request = {
        service: service,
    };

    const [operation] = await runClient.updateService(request, updateMask);
    const [response] = await operation.promise();
    return response;
}