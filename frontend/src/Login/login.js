import axios from "axio s";

export function loginUser(user) {
    return function (dispatch, getState) {
        try {
            axios
                .post('/auth/login', user,
                    setHeaders(getState().authorization.currentUser))
                .then(response => {
                    user = {username: user.username, token: response.data};
                    localStorage.setItem("token", response.data);
                    localStorage.setItem("username", user.username);
                    dispatch(actions.signIn(user));
                })
                .catch(e => {
                    if (e.response.status === 400)
                        dispatch(showMessage({message: e.response.data, isError: true}))
                })
        } catch (e) {
            console.log("SignIn error", e);
        }

    }
}