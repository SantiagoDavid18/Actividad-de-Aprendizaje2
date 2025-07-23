import { useState, useEffect } from "react";
import axios from "axios";

export default function Home() {
  const [prompt, setPrompt] = useState("");
  const [response, setResponse] = useState("");
  const [audio, setAudio] = useState("");
  const [history, setHistory] = useState([]);

  const handleGenerate = async () => {
    if (!prompt.trim()) return alert("Escribe un prompt");

    try {
      const res = await axios.post(`http://localhost:8080/api/activities/generate/1`, {
        message: prompt
      });
      setResponse(res.data.response);
      setAudio(res.data.audioUrl);
      setPrompt("");
      loadHistory();
    } catch (error) {
      console.error(error);
      alert("Error al generar actividad");
    }
  };

  const loadHistory = async () => {
    try {
      const res = await axios.get("http://localhost:8080/api/activities");
      setHistory(res.data);
    } catch (error) {
      console.error(error);
    }
  };

  useEffect(() => {
    loadHistory();
  }, []);

  return (
    <div className="max-w-3xl mx-auto bg-white p-6 rounded shadow">
      <h2 className="text-2xl font-bold mb-4 text-indigo-600">Generar Actividad</h2>
      <textarea
        value={prompt}
        onChange={(e) => setPrompt(e.target.value)}
        className="w-full p-3 border rounded mb-4"
        rows="3"
        placeholder="Escribe tu prompt aquí..."
      ></textarea>
      <button
        onClick={handleGenerate}
        className="bg-indigo-500 text-white px-4 py-2 rounded hover:bg-indigo-700"
      >
        Generar
      </button>

      {response && (
        <div className="mt-4 p-3 bg-gray-100 rounded">
          <h3 className="font-semibold">Respuesta:</h3>
          <p>{response}</p>
          {audio && (
            <audio controls className="mt-2 w-full">
              <source src={`http://localhost:8080${audio}`} type="audio/mpeg" />
            </audio>
          )}
        </div>
      )}

      <h3 className="text-xl font-bold mt-6 mb-2">Historial</h3>
      <ul className="space-y-2">
        {history.map((item) => (
          <li key={item.id} className="p-3 border rounded">
            <p className="font-semibold">{item.prompt}</p>
            <p className="text-sm text-gray-600">{item.response.substring(0, 50)}...</p>
            {item.audioUrl && (
              <audio controls className="mt-1 w-full">
                <source src={`http://localhost:8080${item.audioUrl}`} type="audio/mpeg" />
              </audio>
            )}
          </li>
        ))}
      </ul>
    </div>
  );
}
