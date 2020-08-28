
function countOccurences(target, source){
	var count = 0;
	for(i=0; i < source.length; i++){
		if(source[i] == "t" && source[i+1] == "a" && source[i+2] == "y" && source[i+3] == "l" && source[i+4] == "o" && source[i+5] == "r"){
			count++;

		}
	}
	return count;
}

request = require("request");

request.get("https://www.taylor.edu",
	(error, httpResponse, body) => {
		if (error){
			throw new Error("Request failed");
		}
		let str = `The word 'taylor' occurs ${countOccurences("taylor",body.toLowerCase())} times in the TU home page.`;
		console.log(str);
	});
