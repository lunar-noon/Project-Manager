import { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import axios from "axios";
import AuthService from '../services/auth.service';

export default function Private() {

  const [liste, setListe] = useState(["loading"]);
  const [item, setItem] = useState("");
  const [loading, setLoading] = useState(true);
  const navigate = useNavigate();

  useEffect(() => {
    const user = AuthService.getCurrentUser();
    if (!user) {
      navigate("/login"); // weicher Redirect über React Router
      return;
    }
    axios.get("http://localhost:8080/private", AuthService.getJwtHeader())
    .then(response => {
      setItem(response.data);
      setLoading(false);
    })
  }, [navigate]); // [] --> useEffect wird beim Laden der Seite ausgeführt

  if (loading) return null; // oder ein Ladeindikator

  return (
    <>
      <h1>Private</h1>
      <p>{item}</p>
      <hr />
      <p>{liste}</p>
    </>
  )
}