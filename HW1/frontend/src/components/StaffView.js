import React, { useState, useEffect } from 'react';
import './StaffView.css';

function StaffView() {
  const [allReservations, setAllReservations] = useState([]);
  const [filteredReservations, setFilteredReservations] = useState([]);
  const [searchTerm, setSearchTerm] = useState('');
  const [selectedReservation, setSelectedReservation] = useState(null);
  const [loading, setLoading] = useState(true);
  const [mealDetails, setMealDetails] = useState({}); // Para armazenar os detalhes da refeição
  const [restaurantNames, setRestaurantNames] = useState({}); // Armazenar os nomes dos restaurantes

  // Carregar todas as reservas
  useEffect(() => {
    const fetchReservations = async () => {
      try {
        const response = await fetch('http://localhost:8080/api/reservations');
        const data = await response.json();
        setAllReservations(data);
        setFilteredReservations(data);

        // Para cada reserva, buscar as informações do meal associado
        for (const reservation of data) {
          await fetchMealDetails(reservation.mealId);
        }
      } catch (error) {
        console.error("Erro ao carregar reservas:", error);
      } finally {
        setLoading(false);
      }
    };

    fetchReservations();
  }, []);

  // Buscar detalhes do meal
  const fetchMealDetails = async (mealId) => {
    try {
      const response = await fetch(`http://localhost:8080/api/meals/${mealId}`);
      const data = await response.json();
      // Armazenar os detalhes do meal no estado
      setMealDetails(prevState => ({
        ...prevState,
        [mealId]: data
      }));
      // Agora que os detalhes do meal foram carregados, podemos buscar o nome do restaurante
      await fetchRestaurantName(data.restaurantId); // Passar o restaurantId para pegar o nome
    } catch (error) {
      console.error("Erro ao buscar detalhes da refeição:", error);
    }
  };

  // Buscar o nome do restaurante
  const fetchRestaurantName = async (restaurantId) => {
    // Verifica se o nome do restaurante já foi carregado
    if (!restaurantNames[restaurantId]) {
      try {
        const response = await fetch(`http://localhost:8080/api/restaurants/${restaurantId}`);
        const data = await response.json();
        setRestaurantNames(prevState => ({
          ...prevState,
          [restaurantId]: data.name
        }));
      } catch (error) {
        console.error("Erro ao buscar nome do restaurante:", error);
      }
    }
  };

  // Filtrar reservas
  useEffect(() => {
    const filtered = allReservations.filter(reservation => {
      const searchLower = searchTerm.toLowerCase();
      return (
        (reservation.token && reservation.token.toLowerCase().includes(searchLower)) ||
        (reservation.mealName && reservation.mealName.toLowerCase().includes(searchLower)) ||
        (reservation.meal?.restaurant?.name && reservation.meal.restaurant.name.toLowerCase().includes(searchLower))
      );
    });
    setFilteredReservations(filtered);
  }, [searchTerm, allReservations]);

  const handleVerify = async (token) => {
    try {
      const response = await fetch(`http://localhost:8080/api/reservations/use/${token}`, {
        method: 'PUT',
      });
      if (response.ok) {
        setAllReservations(prev => prev.map(res => 
          res.token === token ? { ...res, used: true } : res
        ));
        setSelectedReservation(prev => prev ? { ...prev, used: true } : null);
        alert("Reserva marcada como utilizada!");
      }
    } catch (error) {
      console.error("Erro ao verificar reserva:", error);
    }
  };

  if (loading) return <div className="loading">Carregando reservas...</div>;

  return (
    <div className="staff-view">
      <h2>Painel de Staff</h2>
      
      <div className="search-container">
        <input
          type="text"
          value={searchTerm}
          onChange={(e) => setSearchTerm(e.target.value)}
          placeholder="Pesquisar por código, refeição ou restaurante..."
          className="search-input"
        />
        <span className="search-results">
          {filteredReservations.length} reservas encontradas
        </span>
      </div>

      <div className="reservations-container">
        <div className="reservations-list">
          <h3>Todas as Reservas</h3>
          {filteredReservations.length > 0 ? (
            <ul>
              {filteredReservations.map(reservation => (
                <li 
                  key={reservation.token}
                  className={`reservation-item ${selectedReservation?.token === reservation.token ? 'selected' : ''}`}
                  onClick={() => setSelectedReservation(reservation)}
                >
                  <div className="reservation-header">
                    <span className="reservation-code">{reservation.token}</span>
                    <span className={`reservation-status ${reservation.used ? 'used' : 'confirmed'}`}>
                      {reservation.used ? 'Utilizada' : 'Confirmada'}
                    </span>
                  </div>

                  {/* Mostrar detalhes da refeição e restaurante dinamicamente */}
                  <p className="reservation-meal">
                    {mealDetails[reservation.mealId]?.name || 'Refeição não especificada'}
                  </p>
                  <p className="reservation-restaurant">
                    {restaurantNames[mealDetails[reservation.mealId]?.restaurantId] || 'Restaurante não especificado'}
                  </p>
                  <p className="reservation-date">
                    {mealDetails[reservation.mealId]?.date || 'Data não especificada'}
                  </p>
                </li>
              ))}
            </ul>
          ) : (
            <p className="no-results">Nenhuma reserva encontrada</p>
          )}
        </div>

        <div className="reservation-details">
          <h3>Detalhes da Reserva</h3>
          {selectedReservation ? (
            <>
              <div className="detail-row">
                <span className="detail-label">Código:</span>
                <span className="detail-value">{selectedReservation.token}</span>
              </div>
              <div className="detail-row">
                <span className="detail-label">Refeição:</span>
                <span className="detail-value">
                  {mealDetails[selectedReservation.mealId]?.name || 'Não especificada'}
                </span>
              </div>
              <div className="detail-row">
                <span className="detail-label">Restaurante:</span>
                <span className="detail-value">
                  {restaurantNames[mealDetails[selectedReservation.mealId]?.restaurantId] || 'Não especificado'}
                </span>
                
              </div>
              <div className="detail-row">
                <span className="detail-label">Data:</span>
                <span className="detail-value">
                    {mealDetails[selectedReservation.mealId]?.date || 'Não especificada'}
                </span>
              </div>
              <div className="detail-row">
                <span className="detail-label">Status:</span>
                <span className={`detail-value status ${selectedReservation.used ? 'used' : 'confirmed'}`}>
                  {selectedReservation.used ? 'Utilizada' : 'Confirmada'}
                </span>
              </div>

              {!selectedReservation.used && (
                <button 
                  onClick={() => handleVerify(selectedReservation.token)}
                  className="verify-btn"
                >
                  <i className="fas fa-check-circle"></i> Marcar como Utilizada
                </button>
              )}
            </>
          ) : (
            <p className="no-selection">Selecione uma reserva para ver detalhes</p>
          )}
        </div>
      </div>
    </div>
  );
}

export default StaffView;
