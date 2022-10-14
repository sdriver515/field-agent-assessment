import { Link } from "react-router-dom";

function NotFound() {
  return (
    <div>
      <h2>Not Found</h2>
      <p>
        Click <Link to="/">here</Link> to go back home.
      </p>
      <div>
        <img
          src="https://media.giphy.com/media/14uQ3cOFteDaU/giphy.gif"
          alt="404 Not Found in front of spinning globe"
        />
      </div>
    </div>
  );
}

export default NotFound;