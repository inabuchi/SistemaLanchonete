const resolvePromise = (data) => {
	return new Promise((resolve, reject)=>{resolve(data)});
}

const rejectPromise = () => {
	return new Promise((resolve, reject)=>{reject()});
}

const actionRequestService = (url, params, type = 'POST') => {
	return $.ajax({
    	headers: { 
            'Accept': 'application/json',
            'Content-Type': 'application/json' 
        },
        type: type, 
        data: JSON.stringify(params),
        dataType: 'json',
        url: url
    });
}

