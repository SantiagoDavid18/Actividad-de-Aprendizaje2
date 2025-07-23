import { useState, useEffect } from 'react';
import axios from 'axios';

function Progress() {
  const [childId, setChildId] = useState('');
  const [progress, setProgress] = useState(null);

  const handleCheckProgress = async () => {
    if (!childId.trim()) return alert('Debes ingresar el ID del niño');

    try {
      const res = await axios.get(`http://localhost:8080/api/activities/progress/${childId}`);
      setProgress(res.data);
    } catch (error) {
      console.error(error);
      alert('Error al obtener el progreso');
    }
  };

  return (
    <div>
      <h2 className="text-2xl font-bold mb-4">Progreso del Niño</h2>

      <input
        type="text"
        value={childId}
        onChange={(e) => setChildId(e.target.value)}
        placeholder="ID del niño"
        className="w-full p-2 border border-gray-300 rounded mb-3"
      />

      <button
        onClick={handleCheckProgress}
        className="bg-green-500 text-white px-4 py-2 rounded hover:bg-green-600"
      >
        Consultar Progreso
      </button>

      {progress && (
        <div className="mt-4 p-3 bg-gray-100 rounded">
          <h3 className="font-semibold mb-2">Resumen:</h3>
          <p>Total de actividades: {progress.totalActivities}</p>
          <p>Porcentaje: {progress.progressPercent}%</p>

          <h4 className="mt-4 font-semibold">Últimas actividades:</h4>
          <ul className="space-y-2">
            {progress.recentActivities.map((act) => (
              <li key={act.id} className="p-2 bg-white rounded shadow">
                <p><strong>Prompt:</strong> {act.prompt}</p>
                <p><strong>Respuesta:</strong> {act.response.substring(0, 60)}...</p>
                {act.audioUrl && (
                  <audio controls className="w-full">
                    <source src={act.audioUrl} type="audio/mpeg" />
                  </audio>
                )}
              </li>
            ))}
          </ul>
        </div>
      )}
    </div>
  );
}

export default Progress;

