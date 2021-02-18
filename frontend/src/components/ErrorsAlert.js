import {Alert} from "react-bootstrap";
import React from "react";

const {Component} = require("react");

class ErrorsAlert extends Component {
  render() {
    const {errors} = this.props;

    if (errors.length > 0) {
      const errorsHtml = errors.map((error) => {
        return <li>{error.message}</li>
      })

      return (
        <Alert variant="danger">
          <Alert.Heading>Errors:</Alert.Heading>
          <ul>
            {errorsHtml}
          </ul>
        </Alert>
      )
    } else {
      return ("")
    }
  }
}

export default ErrorsAlert
