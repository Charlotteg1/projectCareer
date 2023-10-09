import { useEffect, useState } from 'react';
import './ChatGPT.css';

const ChatGPT = () => {

    const [entryValue, setEntryValue]= useState("");
    const [idEntryValue, setIdEntryValue]= useState("");
    const [response, setResponse] = useState('');
    const [currentUser, setCurrentUser]= useState("");

    const postRequest=async(input)=>{
        try {
            const url = `http://localhost:8080/`;
            const response = await fetch(url, {
              method: "POST",
              headers: { "Content-Type": "text/plain" },
              body: input,
            });
        
            if (!response.ok) {
              throw new Error('Network response was not ok');
            }
        
            const data = await response.text();
        
            // Set the response data in the component's state
            setResponse(data);
            console.log(data)
          } catch (error) {
            console.error('There was a problem with the fetch operation:', error);
          }
       
    }
    const handleRequest = async(e) =>{
        e.preventDefault();
        await postRequest(entryValue)
    }

    const fetchEmployee= async ()=>{
      const response = await fetch("http://localhost:8080/users/"+ idEntryValue)
      const data = await response.json();
      if(!response.ok){
        alert("employee id in valid please try again")
      }else{
        setCurrentUser(data)
      }

    }

    useEffect=(()=>{
      if(entryValue===""){
        setResponse("")
        console.log(response)
      }
    },[entryValue])


// create form and get response
    return (<>
    <h1>Chat GPT send and response</h1>
    {!currentUser && (<form className="form" onSubmit={fetchEmployee}>
		    <fieldset>
          <h4> enter employee id</h4>
		        <div className="form-group">
		            <input type="text" name="prompt" onChange={(e)=>{setIdEntryValue(e.target.value)}}required autoFocus/>
		        </div>
		        <div className="row">
		            <input type="submit" value="Send" />
		        </div>
		    </fieldset>
        </form>)}
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

        <div>
        {response && (<div><h2>ChatGPT responded with:</h2>
        <p>{response}</p> </div>)}
        
      </div>
    </>)
}
export default ChatGPT;