import React, { useState } from 'react';
import UserView from './components/UserView';
import StaffView from './components/StaffView';
import './App.css';

function App() {
  const [view, setView] = useState('user'); // 'user' ou 'staff'

  return (
    <div className="app">
      <header className="app-header">
        <h1>Meal Booking</h1>
        <button 
          className="view-toggle"
          onClick={() => setView(view === 'user' ? 'staff' : 'user')}
        >
          {view === 'user' ? 'Modo Staff' : 'Modo Utilizador'}
        </button>
      </header>

      <main className="app-main">
        {view === 'user' ? <UserView /> : <StaffView />}
      </main>
    </div>
  );
}

export default App;