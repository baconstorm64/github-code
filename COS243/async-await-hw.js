const moment = require("moment");
const request = require("request");
const uri = "https://api.openweathermap.org/data/2.5/weather";
const qs = {
	id: "4927510",
	appid:"32843bad9e96bb36c7935458544b1628",
	units: "imperial"
};


function reportWeather(result){
	console.log(`On ${moment()}, it's ${JSON.parse(result).main.temp} degrees.`);
}

function reportError(error){
	console.log(`You dun messed up: '${error}'`);
}

function requestPromisified(uri, qs){
	return new Promise((resolve,reject) =>{
		request(`${uri}?appid=${qs.appid}&id=${qs.id}&units=${qs.units}`, (err, weather) =>{
			if(err){
			    reject(error);
			}
			else{
			    resolve(weather.body);
			}
		});
	});
}


function getTimeAndTempPromise(uri, qs){
	requestPromisified(uri, qs)
	  .then(weather => reportWeather(weather))
	  .catch(error => reportError(error));
}

async function getTimeAndTempAsync(uri, qs){
	try{
	    let weather = await requestPromisified(uri, qs);
	    reportWeather(weather);
	}
	catch (error){
	    reportError(error);
	}
}

getTimeAndTempPromise(uri, qs);
getTimeAndTempAsync(uri, qs);
