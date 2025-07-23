import { BrowserRouter as Router, Routes, Route, Link } from "react-router-dom";
import GenerateActivity from "./components/GenerateActivity";
import Progress from "./components/Progress";

function App() {
  return (
    <Router>
      <div className="min-h-screen bg-gray-100 p-6">
        <h1 className="text-3xl font-bold mb-6 text-indigo-600">SpeechDown 🚀</h1>

        <nav className="mb-6 space-x-4">
          <Link to="/" className="text-indigo-600 font-semibold hover:underline">Generar Actividad</Link>
          <Link to="/progress" className="text-indigo-600 font-semibold hover:underline">Progreso</Link>
        </nav>

        <Routes>
          <Route path="/" element={<GenerateActivity />} />
          <Route path="/progress" element={<Progress />} />
        </Routes>
      </div>
    </Router>
  );
}

export default App;


