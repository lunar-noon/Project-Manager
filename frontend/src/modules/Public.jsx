import { useEffect, useState } from 'react';
import axios from "axios";

export default function Public() {

  const [liste, setListe] = useState(["Item 1", "Item 2", "Item 3"]);
  const [item, setItem] = useState("");

  const buttonClicked = (event) => {
    console.log("button clicked: " + event.target.innerText);
  }

  useEffect(() => {
    axios.get("http://localhost:8080/public")
    .then(response => {
      setListe(response.data);
    })
  }, []); // [] --> useEffect wird beim Laden der Seite ausgeführt

  const postItem = (event) => {
    if (!item.trim()) return;
    axios.post("http://localhost:8080/public", item, {
      headers: {
        'Content-Type': 'text/plain'
      }
    })
    .then(() => {
      // Re-fetch list after successful POST
      return axios.get("http://localhost:8080/public").then(response => {
        setListe(response.data);
        setItem("");
      });
    })
  }

  return (
    <div>
      <h1>Public Page</h1>
      <p>This is a public page accessible to everyone.</p>
      <hr />
      <button onClick={buttonClicked}>Button 1</button>
      <button onClick={buttonClicked}>Button 2</button>
      <button onClick={buttonClicked}>Button 3</button>
      <hr />
      <p>New Item: </p>
      <form onSubmit={postItem}>
        <input type='text' value={item}
          onChange={(event) => setItem(event.target.value)}
          />
        <button type='submit'>Add Item</button>
      </form>
      
      <ul style={{ maxHeight: '40vh', overflowY: 'scroll' }}>
        {liste.map((item, index) => {
          return <li key={index}>{item}</li>
        })}
      </ul>
    </div>
  );
}