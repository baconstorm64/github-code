
const request = require('request');
const moment = require('moment');

function specialTime(){
	return moment().format("HH:mm:ss.SSS");
	
}

function regularTime(){
	return moment().format("HH:mm");
}

function asynchronousWeather(){

	request('https://api.openweathermap.org/data/2.5/weather?appid=32843bad9e96bb36c7935458544b1628&id=4927510&units=imperial', (err, weather) =>{
		if(err){
		    console.log(`I don't know what this means, but here's an error: ${err}`);
		}
		else{
		    console.log(`At ${regularTime()} it is ${JSON.parse(weather.body).main.temp} degrees.`);
		}

	});
}


asynchronousWeather();
