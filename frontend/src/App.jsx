import Layout from './modules/Layout'
import Home from './modules/Home'
import About from './modules/About'
import Public from './modules/Public'
import Projects from './modules/Projects'
import Private from './modules/Private'
import Login from './modules/Login'
import Signup from './modules/Signup'
import Logout from './modules/Logout'
import NoPage from './modules/NoPage'
import { Routes } from 'react-router-dom'
import { Route } from 'react-router-dom'
import './App.css'

function App() {
  return (
    <Routes>
      <Route path="/" element={<Layout />}>
        <Route index element={<Home />} />
        <Route path="about" element={<About />} />
        <Route path="public" element={<Public />} />
        <Route path="projects" element={<Projects />} />
        <Route path="private" element={<Private />} />
        <Route path="login" element={<Login />} />
        <Route path="signup" element={<Signup />} />
        <Route path="logout" element={<Logout />} />
        <Route path="*" element={<NoPage />} />
      </Route>
    </Routes>
  )

}

export default App
