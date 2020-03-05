import React from 'react';
import axios from 'axios';
import './App.css';
import {TwitterProfile} from "./TwitterProfile";

class App extends React.Component {
  constructor(props) {
    super(props);
    this.state = { username: '', twitterInfo: {} };
    this.handleChange = this.handleChange.bind(this);
    this.handleSubmit = this.handleSubmit.bind(this);
  }

  handleChange(e) {
    this.setState({ username: e.target.value });
  }

  async handleSubmit(e) {
    e.preventDefault();
    if (!this.state.username.length) {
      return;
    }
    const url = (window.LAMBDA_URL || 'https://kj1ox23jfl.execute-api.eu-west-1.amazonaws.com');
    const response = await axios.get(`${url}/prod?username=${this.state.username}`)
    this.setState(state => ({
      twitterInfo: response.data,
      username: ''
    }));
  }

  render() {
    return (
        <div className="App">
          <h3>Twitter Profile Viewer</h3>
          <form onSubmit={this.handleSubmit}>
            <TwitterProfile {...this.state.twitterInfo}/>
            <input
                id="username"
                placeholder="enter twitter username"
                onChange={this.handleChange}
                value={this.state.username}
            />
            <button>
              Go get
            </button>
          </form>
        </div>
    );
  }

}

export default App;
