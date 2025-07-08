import { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import axios from "axios";
import AuthService from '../services/auth.service';

export default function About(){

  const [project, setProject] = useState({ name: '', description: '' });
  const [projects, setProjects] = useState([]);
  const [loading, setLoading] = useState(true);
  const navigate = useNavigate();

  useEffect(() => {
    const user = AuthService.getCurrentUser();
    if (!user) {
      navigate("/login"); // weicher Redirect über React Router
      return;
    }
    axios.get("http://localhost:8080/projects", AuthService.getJwtHeader())
    .then(response => {
      setProjects(response.data);
      setLoading(false);
    })
  }, [navigate]); // [] --> useEffect wird beim Laden der Seite ausgeführt

  if (loading) return null; // oder ein Ladeindikator

  const postItem = (event) => {
    event.preventDefault();
    if (!project.name.trim() || !project.description.trim()) return;
    axios.post("http://localhost:8080/projects", project, {
      headers: {
        'Content-Type': 'application/json',
        ...AuthService.getJwtHeader().headers
      }
    })
    .then(() => {
      // Re-fetch list after successful POST
      axios.get("http://localhost:8080/projects", AuthService.getJwtHeader())
      .then(response => {
        setProjects(response.data);
        setProject({ name: '', description: '' });
      });
    })
  }


  return (
    <>
      <div>
        <h1>Projects</h1>
        <p>Welcome user, you have access to this private endpoint.</p>
        <hr />
        <form onSubmit={postItem}>
          <label htmlFor="project-name">Project Name:</label>
          <input id='project-name' type='text' value={project.name}
            onChange={(event) => setProject({ ...project, name: event.target.value })}
          />
          <label htmlFor="project-description">Project Description</label>
          <textarea id='project-description' rows='4' cols='50' value={project.description}
            onChange={(event) => setProject({ ...project, description: event.target.value })}
          />

          <button type='submit'>Add Project</button>
        </form>
      </div>
      <div>
        <ul style={{ maxHeight: '40vh', overflowY: 'scroll' }}>
          {projects.map((project, index) => {
            return <li key={index}>{project}</li>
          })}
        </ul>
      </div>
    </>
  )
}