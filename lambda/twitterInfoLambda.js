const twitterClient = require('./client/twitterClient');

exports.main = async function(event, context) {
    try {
        const method = event.httpMethod;

        if (method === 'GET' && event.path === '/') {
            return await getTwitterInfo(event);
        }

        return blockAllOtherRequests();
    } catch(error) {
        return handleError(error);
    }
};

async function getTwitterInfo(event) {
    const queryParams = event.queryStringParameters;
    const {username} = queryParams;
    const info = await twitterClient.getTwitterInfoFor(username);
    return {
        statusCode: 200,
        headers: {
            "Access-Control-Allow-Origin" : "*"
        },
        body: JSON.stringify(info)
    };
}

function blockAllOtherRequests() {
    return {
        statusCode: 400,
        headers: {
            "Access-Control-Allow-Origin" : "*"
        },
        body: "We only accept GET /"
    };
}

function handleError(error) {
    const body = error.stack || JSON.stringify(error, null, 2);
    return {
        statusCode: 400,
        headers: {
            "Access-Control-Allow-Origin" : "*"
        },
        body: JSON.stringify(body)
    }
}
