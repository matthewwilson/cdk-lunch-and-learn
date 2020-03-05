const axios = require('axios');
const querystring = require('querystring');

async function getAccessToken() {
    const apiKey = "cNXfoXhtGrdfkBgDwFZbOgmpq";
    const apiSecret = "yJ50mhcyDRuCEpQxfwT8gQeWvJkxUEZFAJhk2kskyGOzbzDvbO";

    const body = {
        'grant_type': 'client_credentials'
    };

    const config = {
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded'
        },
        auth: {
            username: apiKey,
            password: apiSecret
        }
    };

    const response = await axios.post('https://api.twitter.com/oauth2/token', querystring.stringify(body), config);
    return response.data['access_token'];
}

exports.getTwitterInfoFor = async (username) => {
    const accessToken = await getAccessToken();
    const config = {
        headers: {
            'Authorization': `Bearer ${accessToken}`
        }
    };

    const response = await axios.get(`https://api.twitter.com/1.1/users/show.json?screen_name=${username}`, config);
    return response.data;
}
