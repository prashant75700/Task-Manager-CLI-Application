import { useState, useEffect } from 'react';
import './App.css';

function App() {
  const [tasks, setTasks] = useState([]);
  const [stats, setStats] = useState({ total: 0, completed: 0, pending: 0, high: 0, medium: 0, low: 0 });
  const [formData, setFormData] = useState({ title: '', description: '', priority: 'LOW' });
  const [loading, setLoading] = useState(true);

  const [editingId, setEditingId] = useState(null);

  const [filter, setFilter] = useState('ALL');

  const API_URL = import.meta.env.VITE_API_URL || 'http://localhost:5000/api';

  const fetchData = async () => {
    try {
      const tasksRes = await fetch(`${API_URL}/tasks`);
      const statsRes = await fetch(`${API_URL}/stats`);

      const tasksData = await tasksRes.json();
      const statsData = await statsRes.json();

      setTasks(tasksData);
      setStats(statsData);
      setLoading(false);
    } catch (error) {
      console.error('Error fetching data:', error);
      setLoading(false);
    }
  };

  useEffect(() => {
    fetchData();
  }, []);

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const url = editingId ? `${API_URL}/tasks/${editingId}` : `${API_URL}/tasks`;
      const method = editingId ? 'PUT' : 'POST';

      await fetch(url, {
        method: method,
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(formData)
      });

      setFormData({ title: '', description: '', priority: 'LOW' });
      setEditingId(null);
      fetchData();
    } catch (error) {
      console.error('Error saving task:', error);
    }
  };

  const toggleComplete = async (id) => {
    try {
      await fetch(`${API_URL}/tasks/${id}/toggle`, { method: 'PUT' });
      fetchData();
    } catch (error) {
      console.error('Error toggling task:', error);
    }
  };

  const deleteTask = async (id) => {
    try {
      await fetch(`${API_URL}/tasks/${id}`, { method: 'DELETE' });
      fetchData();
    } catch (error) {
      console.error('Error deleting task:', error);
    }
  };

  const startEditing = (task) => {
    setEditingId(task.id);
    setFormData({
      title: task.title,
      description: task.description,
      priority: task.priority
    });
  };

  const cancelEdit = () => {
    setEditingId(null);
    setFormData({ title: '', description: '', priority: 'LOW' });
  };

  // Filtering Logic
  const getFilteredTasks = () => {
    switch (filter) {
      case 'COMPLETED': return tasks.filter(t => t.isCompleted);
      case 'PENDING': return tasks.filter(t => !t.isCompleted);
      case 'HIGH': return tasks.filter(t => t.priority === 'HIGH');
      default: return tasks;
    }
  };

  const filteredTasks = getFilteredTasks();

  return (
    <div className="container">
      <header>
        <h1>✨ Task Master</h1>
        <div className="user-profile">
          {/* Placeholder for user profile if needed */}
        </div>
      </header>

      <div className="stats-grid">
        <StatCard
          label="Total Tasks"
          value={stats.total}
          onClick={() => setFilter('ALL')}
          isActive={filter === 'ALL'}
        />
        <StatCard
          label="Completed"
          value={stats.completed}
          color="var(--success)"
          onClick={() => setFilter('COMPLETED')}
          isActive={filter === 'COMPLETED'}
        />
        <StatCard
          label="Pending"
          value={stats.pending}
          color="var(--warning)"
          onClick={() => setFilter('PENDING')}
          isActive={filter === 'PENDING'}
        />
        <StatCard
          label="High Priority"
          value={stats.high}
          color="#da3633"
          onClick={() => setFilter('HIGH')}
          isActive={filter === 'HIGH'}
        />
      </div>

      <div className="main-content">
        <aside>
          <div className="form-card">
            <h2>{editingId ? 'Edit Task' : 'Add New Task'}</h2>
            <form onSubmit={handleSubmit}>
              <div className="form-group">
                <label>Title</label>
                <input
                  type="text"
                  value={formData.title}
                  onChange={(e) => setFormData({ ...formData, title: e.target.value })}
                  placeholder="What needs to be done?"
                  required
                />
              </div>
              <div className="form-group">
                <label>Description</label>
                <textarea
                  value={formData.description}
                  onChange={(e) => setFormData({ ...formData, description: e.target.value })}
                  placeholder="Add details..."
                  rows="3"
                />
              </div>
              <div className="form-group">
                <label>Priority</label>
                <select
                  value={formData.priority}
                  onChange={(e) => setFormData({ ...formData, priority: e.target.value })}
                >
                  <option value="LOW">Low</option>
                  <option value="MEDIUM">Medium</option>
                  <option value="HIGH">High</option>
                </select>
              </div>
              <div style={{ display: 'flex', gap: '0.5rem' }}>
                <button type="submit" className="btn-primary">
                  {editingId ? 'Update Task' : 'Create Task'}
                </button>
                {editingId && (
                  <button type="button" onClick={cancelEdit} className="btn-secondary" style={{ background: 'var(--text-secondary)', padding: '0.75rem', borderRadius: '6px', color: 'white' }}>
                    Cancel
                  </button>
                )}
              </div>
            </form>
          </div>
        </aside>

        <section className="tasks-section">
          <div style={{ display: 'flex', justifyContent: 'space-between', alignItems: 'center', marginBottom: '1.5rem' }}>
            <h2 style={{ margin: 0 }}>My Tasks <span style={{ fontSize: '1rem', color: 'var(--text-secondary)', fontWeight: 'normal' }}>({filter === 'ALL' ? 'All' : filter})</span></h2>
            {filter !== 'ALL' && (
              <button
                onClick={() => setFilter('ALL')}
                style={{ background: 'transparent', color: 'var(--accent-color)', fontSize: '0.9rem', padding: 0, textDecoration: 'underline' }}
              >
                Clear Filter
              </button>
            )}
          </div>
          <div className="task-list">
            {loading ? <p>Loading...</p> : filteredTasks.map(task => (
              <div key={task.id} className={`task-card ${task.isCompleted ? 'completed' : ''}`}>
                <div className="task-info">
                  <h3>{task.title}</h3>
                  <p className="task-desc">{task.description}</p>
                  <div className="task-meta">
                    <span className={`priority-badge priority-${task.priority}`}>
                      {task.priority}
                    </span>
                    <span>{task.isCompleted ? 'Completed' : 'Pending'}</span>
                  </div>
                </div>
                <div className="task-actions">
                  <button className="btn-icon btn-edit" title="Edit" onClick={() => startEditing(task)}>
                    <svg width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" strokeWidth="2" strokeLinecap="round" strokeLinejoin="round">
                      <path d="M11 4H4a2 2 0 0 0-2 2v14a2 2 0 0 0 2 2h14a2 2 0 0 0 2-2v-7"></path>
                      <path d="M18.5 2.5a2.121 2.121 0 0 1 3 3L12 15l-4 1 1-4 9.5-9.5z"></path>
                    </svg>
                  </button>
                  <button className="btn-icon btn-check" title="Toggle Complete" onClick={() => toggleComplete(task.id)}>
                    <svg width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" strokeWidth="2" strokeLinecap="round" strokeLinejoin="round">
                      <polyline points="20 6 9 17 4 12"></polyline>
                    </svg>
                  </button>
                  <button className="btn-icon btn-delete" title="Delete" onClick={() => deleteTask(task.id)}>
                    <svg width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" strokeWidth="2" strokeLinecap="round" strokeLinejoin="round">
                      <polyline points="3 6 5 6 21 6"></polyline>
                      <path d="M19 6v14a2 2 0 0 1-2 2H7a2 2 0 0 1-2-2V6m3 0V4a2 2 0 0 1 2-2h4a2 2 0 0 1 2 2v2"></path>
                    </svg>
                  </button>
                </div>
              </div>
            ))}
            {!loading && filteredTasks.length === 0 && (
              <div style={{ textAlign: 'center', color: 'var(--text-secondary)', padding: '2rem' }}>
                <p>No tasks found for this filter.</p>
              </div>
            )}
          </div>
        </section>
      </div>
    </div>
  );
}

function StatCard({ label, value, color, onClick, isActive }) {
  return (
    <div
      className={`stat-card ${isActive ? 'active' : ''}`}
      style={{ borderColor: isActive ? (color || 'var(--accent-color)') : 'var(--border-color)', cursor: 'pointer' }}
      onClick={onClick}
    >
      <div className="stat-value" style={{ color: color }}>{value}</div>
      <div className="stat-label">{label}</div>
    </div>
  )
}

export default App;
