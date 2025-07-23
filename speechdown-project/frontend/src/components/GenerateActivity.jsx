import { useState, useEffect } from 'react';

function GenerateActivity() {
  const [childId, setChildId] = useState('');
  const [prompt, setPrompt] = useState('');
  const [response, setResponse] = useState(null);
  const [history, setHistory] = useState([]);

  // Generar actividad
  const handleGenerate = async () => {
    if (!childId.trim() || !prompt.trim()) {
      return alert('Debes ingresar el ID del niño y un mensaje.');
    }

    try {
      const res = await fetch(`http://localhost:8080/api/activities/generate/${childId}`, {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ message: prompt })
      });

      const data = await res.json();
      setResponse(data);
      setPrompt('');
      loadHistory();
    } catch (error) {
      console.error(error);
      alert('Error al generar la respuesta');
    }
  };

  // Cargar historial
  const loadHistory = async () => {
    if (!childId) return;
    try {
      const res = await fetch(`http://localhost:8080/api/activities/child/${childId}`);
      const data = await res.json();
      setHistory(data);
    } catch (error) {
      console.error(error);
    }
  };

  return (
    <div>
      <h2 className="text-2xl font-bold mb-4">Generar Actividad</h2>

      <input
        type="text"
        value={childId}
        onChange={(e) => setChildId(e.target.value)}
        placeholder="ID del niño"
        className="w-full p-2 border border-gray-300 rounded mb-3"
      />

      <textarea
        value={prompt}
        onChange={(e) => setPrompt(e.target.value)}
        className="w-full p-3 border border-gray-300 rounded mb-4"
        rows="4"
        placeholder="Escribe tu prompt aquí..."
      ></textarea>

      <button
        onClick={handleGenerate}
        className="bg-indigo-500 text-white px-4 py-2 rounded hover:bg-indigo-600"
      >
        Generar
      </button>

      {response && (
        <div className="mt-4 p-3 bg-gray-100 rounded">
          <h3 className="font-semibold mb-2">Respuesta generada:</h3>
          <p className="mb-2">{response.response}</p>
          {response.audioUrl && (
            <audio controls className="w-full">
              <source src={response.audioUrl} type="audio/mpeg" />
              Tu navegador no soporta audio.
            </audio>
          )}
        </div>
      )}

      <div className="mt-6">
        <h3 className="text-xl font-semibold mb-2">Historial</h3>
        <ul className="space-y-2">
          {history.map((item) => (
            <li key={item.id} className="p-3 bg-white shadow rounded">
              <p className="font-bold">Prompt: {item.prompt}</p>
              <p className="text-gray-600 mb-2">Respuesta: {item.response.substring(0, 80)}...</p>
              {item.audioUrl && (
                <audio controls className="w-full">
                  <source src={item.audioUrl} type="audio/mpeg" />
                </audio>
              )}
            </li>
          ))}
        </ul>
      </div>
    </div>
  );
}

export default GenerateActivity;

