import { Link } from "react-router-dom";

export default function Navbar() {
  return (
    <nav className="bg-indigo-600 text-white p-4 flex justify-between">
      <h1 className="text-xl font-bold">SpeechDown</h1>
      <div className="space-x-4">
        <Link to="/" className="hover:underline">Inicio</Link>
        <Link to="/profiles" className="hover:underline">Perfiles</Link>
      </div>
    </nav>
  );
}
