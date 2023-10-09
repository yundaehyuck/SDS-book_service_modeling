import axios from 'axios'
import api from '@/api/auth'

const searchByISBN = isbn => api.get(`/api/books/${isbn}`)
const getSearchList = (request) => axios.get(`/api/books?keyword=${request.keyword}&searchTargets=${request.searchTargets}&page=${request.page}`)
const getISBNfromPicture = formData => axios.post(`/fast/books/isbn`, formData, {headers: {'Content-Type': 'multipart/form-data'}})
const getNewBookList = request => axios.get(`/api/books/new`)
export { searchByISBN, getSearchList, getISBNfromPicture, getNewBookList }
