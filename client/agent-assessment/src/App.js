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
      <h1>A Very Secret Agent Tracker</h1>
      <Switch>
        <Route path="/agents" exact>
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
        <Route path="/">
        <Navigation />
        <h2>Welcome to home base!</h2>
        <h3>Please click one of the sections above to explore options.</h3>
        </Route>
        <Route path="*">
        <NotFound />
        </Route>
      </Switch>
    </Router>
  );
}

export default App;