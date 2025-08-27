import React, { useEffect, useState } from 'react';
import axios from 'axios';
import './App.css';

// Define a type for a single log object to match our backend
interface Log {
  id: string;
  timestamp: string;
  service: string;
  level: string;
  message: string;
}

function App() {
  // Create a state variable to hold our array of logs
  const [logs, setLogs] = useState<Log[]>([]);

  // useEffect runs once when the component first loads
  useEffect(() => {
    // Make an API call to our backend's GET endpoint
    axios.get('http://localhost:8080/api/v1/logs')
      .then(response => {
        // The actual log data is inside response.data.content
        setLogs(response.data.content);
      })
      .catch(error => {
        console.error("There was an error fetching the logs!", error);
      });
  }, []); // The empty array [] means this effect runs only once

  return (
    <div className="App">
      <header className="App-header">
        <h1>Project Titan - Log Viewer</h1>
      </header>
      <main className="log-container">
        {logs.map(log => (
          <div key={log.id} className="log-entry">
            <span className="log-timestamp">{new Date(log.timestamp).toLocaleString()}</span>
            <span className={`log-level log-level-${log.level.toLowerCase()}`}>{log.level}</span>
            <span className="log-service">{log.service}</span>
            <span className="log-message">{log.message}</span>
          </div>
        ))}
      </main>
    </div>
  );
}

export default App;