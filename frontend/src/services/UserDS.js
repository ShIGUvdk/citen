import http from '../http-common'

class UserDS{
    getAll(){
        return http.get('/user/all')
    }
}

export default new UserDS()