import { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import axios from "axios";

export default function Progress() {
  const { childId } = useParams();
  const [progress, setProgress] = useState(null);

  useEffect(() => {
    axios.get(`http://localhost:8080/api/activities/progress/${childId}`)
      .then(res => setProgress(res.data));
  }, [childId]);

  if (!progress) return <p>Cargando...</p>;

  return (
    <div className="max-w-xl mx-auto bg-white p-6 rounded shadow">
      <h2 className="text-2xl font-bold mb-4">Progreso del Niño #{progress.childId}</h2>
      <p>Total de actividades: {progress.totalActivities}</p>
      <p>Progreso: {progress.progressPercent}%</p>
      <h3 className="mt-4 font-semibold">Últimas 3 actividades:</h3>
      <ul>
        {progress.recentActivities.map((a) => (
          <li key={a.id} className="border p-2 mt-2">
            <p>{a.prompt}</p>
            <audio controls>
              <source src={`http://localhost:8080${a.audioUrl}`} type="audio/mpeg" />
            </audio>
          </li>
        ))}
      </ul>
    </div>
  );
}
