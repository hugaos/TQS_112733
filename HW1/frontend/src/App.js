// src/App.js
import React from 'react';
import Navbar from './components/Navbar';
import RestaurantList from './components/RestaurantList';
import './App.css';

function App() {
  return (
    <div>
      <Navbar />  {/* Componente Navbar com o título */}
      <div className="container">
        <RestaurantList />
      </div>
    </div>
  );
}

export default App;
