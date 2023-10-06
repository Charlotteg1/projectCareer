import { useState } from 'react';
import './ChatGPT.css';

const ChatGPT = () => {

    const [entryValue, setEntryValue]= useState("")

    const postRequest=async(input)=>{
        const url = `http://localhost:8080/`;
        const response = await fetch(url, {
            method: "POST",
            headers: {"Content-Type": "text/plain"},
            body: input ,
        }).then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            return response.text(); // Read the response body as text
        })
        .then(data => {
            console.log(data); // This will log the response body as a string
        })
        .catch(error => {
            console.error('There was a problem with the fetch operation:', error);
        })
        return(<div>
            <h2 >ChatGPT responded with: </h2>
            <p if="${response != null}" text="${response}" />
        </div>)
    }
    const handleRequest = async(e) =>{
        e.preventDefault();
        await postRequest(entryValue)
    }

// create form and get response
    return (<>
    <h1>Chat GPT send and response</h1>
        <form className="form" onSubmit={handleRequest}>
		    <fieldset>
		        <div className="form-group">
		            <input type="text" name="prompt" onChange={(e)=>{setEntryValue(e.target.value)}}required autoFocus/>
		        </div>
		        <div className="row">
		            <input type="submit" value="Send" />
		        </div>
		    </fieldset>
        </form>
    </>)
}
export default ChatGPT;