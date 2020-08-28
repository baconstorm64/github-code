const https = require('https');
const debug = false;

function prettyPrintJson (jsonObject){
	return JSON.stringify(jsonObject, null, 2);
}

function randomOrgApiCallback(requestOptions, postData){
	return new Promise((resolve, reject) => {
		if(!postData){
		   reject();
		}
		else{
			const request = https.request(requestOptions, response => {
				const chunks = [];

				if (debug) {
		    		    console.log(`STATUS: ${response.statusCode}`);
		    		    console.log(`HEADERS: ${prettyPrintJson(response.headers)}`);
				}
				response.setEncoding('utf8');

				response.on('data', chunk => {
					if (debug) {
			    		console.log(`CHUNK: ${prettyPrintJson(JSON.parse(chunk))}`);
					}
					chunks.push(chunk);
				});

				response.on('end', () => {
					const content = chunks.join('');
					console.log(`CONTENT (Callback):\n${prettyPrintJson(JSON.parse(content))}`);
				});
			});

			request.on('error', err => {
				console.error(`Request error: ${err}`);
			});

			request.write(JSON.stringify(postData));
			request.end();
		}
	});
}

const randomOrgRequestOptions = {
	hostname: 'api.random.org',
	method: 'POST',
	path: '/json-rpc/2/invoke',
	headers: { 'Content-Type': 'application/json'}
};

const generateIntegersPostData = {
	"jsonrpc": "2.0",
	"method": "generateIntegers",
	"params": {
		"apiKey": "ccba1f8a-21ab-48c6-b62d-e8d34bb19c36",
		"n": 5,
		"min": 1,
		"max": 100,
	},
	"id": 1
};

function randomOrgApiPromise(requestOptions, postData){
	console.log("This will print before the request");
	randomOrgApiCallback(requestOptions, postData)
	  .then(content => console.log(`CONTENT (Promise):\n${prettyPrintJson(JSON.parse(content))}`))
	  .catch(error => console.log(`BUMMER: ${error}`));
	console.log("This will also print before the request because it's ASYNCHRONOUS");
}


randomOrgApiPromise(randomOrgRequestOptions, generateIntegersPostData)

//randomOrgApiCallback(randomOrgRequestOptions, generateIntegersPostData);
