import ManageProduct from "./components/ManageProduct";
import AddProduct from "./components/AddProduct";
import EditProduct from "./components/EditProduct";

import { BrowserRouter as Router, Switch, Route } from "react-router-dom";


function App() {
  return (
      <div>
        {/* <ManageProduct /> */}
        {/* <AddProduct /> */}

        <Router>
          <Switch>
            <Route path="/" exact component={ManageProduct} />
            <Route path="/add" exact component={AddProduct} />
            <Route path="/edit" exact component={EditProduct} />
          </Switch>
        </Router>
      </div>
  );

}
export default App;