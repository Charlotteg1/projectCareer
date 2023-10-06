import { BrowserRouter, Route, Routes } from "react-router-dom";
import HomePage from "../Components/HomePage/HomePage";
import ChatGPT from "../Components/ChatGPT/ChatGPT";




const Container = ()=> {




    return (
        <BrowserRouter>
                <Routes>
                    <Route path="/" element={<HomePage/>}/> 
                    <Route path="/Page1" element={<ChatGPT/>}/>
                </Routes>
        </BrowserRouter>
    )
}
export default Container;
