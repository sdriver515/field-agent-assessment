import { useEffect } from "react";
import { BrowserRouter as Router, Switch, Route } from "react-router-dom";

import About from "./components/About";
import Contact from "./components/Contact";
import Navigation from "./components/Navigation";
import NotFound from "./components/NotFound";
import AgentForm from "./components/AgentForm";
import AgentList from "./components/AgentList";

function App() {
  return (
    <Router>
      <h1>Welcome To The Agent Tracker</h1>
      <Navigation />
      <Switch>
        <Route path="/" exact>
          <AgentList />
        </Route>
        <Route path="/about">
          <About />
        </Route>
        <Route path="/contact">
          <Contact />
        </Route>
        <Route path={["/agents/add", "/agents/edit/:agentId"]}>
          <AgentForm />
        </Route>
        <Route path="*">
          <NotFound />
        </Route>
      </Switch>
    </Router>
  );
}

export default App;