import { useState, useEffect } from "react";
import axios from "axios";

export default function ChildProfiles() {
  const [profiles, setProfiles] = useState([]);
  const [name, setName] = useState("");

  const loadProfiles = async () => {
    const res = await axios.get("http://localhost:8080/api/child-profiles");
    setProfiles(res.data);
  };

  const addProfile = async () => {
    if (!name.trim()) return;
    await axios.post("http://localhost:8080/api/child-profiles/1", { name });
    setName("");
    loadProfiles();
  };

  useEffect(() => {
    loadProfiles();
  }, []);

  return (
    <div className="max-w-xl mx-auto bg-white p-6 rounded shadow">
      <h2 className="text-2xl font-bold mb-4">Perfiles de niños</h2>
      <div className="flex gap-2 mb-4">
        <input
          type="text"
          value={name}
          onChange={(e) => setName(e.target.value)}
          className="border p-2 flex-1"
          placeholder="Nombre del niño"
        />
        <button onClick={addProfile} className="bg-indigo-500 text-white px-4 rounded">
          Agregar
        </button>
      </div>
      <ul>
        {profiles.map((p) => (
          <li key={p.id} className="p-2 border-b">{p.name}</li>
        ))}
      </ul>
    </div>
  );
}
