import { HttpHeaders, HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class BookService {

  BASE_URL = "http://ec2-52-15-88-109.us-east-2.compute.amazonaws.com:8080/api/v1/digitalbooks/"
  requestHeader = new HttpHeaders({ "Authorization": "Bearer " + localStorage.getItem('jwtToken') })

  constructor(private httpClient: HttpClient) { }

  searchBook(data: any) {
    const header = new HttpHeaders({ "No-Auth": "True" })
    var url = this.BASE_URL + 'search?'
    Object.entries(data).forEach(([key, value]) => {
      url += `${key}=${value}&`
    });
    return this.httpClient.get(url, { headers: header });
  }

  addBook(data: any) {
    var url = this.BASE_URL + `author/${JSON.parse(localStorage.getItem('userDetails')!).userId}/book`
    return this.httpClient.post(url, data);
  }

  getMyBooks() {
    var url = this.BASE_URL + `author/${JSON.parse(localStorage.getItem('userDetails')!).userId}/books`
    return this.httpClient.get(url);
  }

  updateBookBlock(bookId: number, block: boolean) {
    var url = this.BASE_URL + `author/${JSON.parse(localStorage.getItem('userDetails')!).userId}/books/${bookId}?block=${block}`
    return this.httpClient.post(url, {});
  }

  updateBook(bookData: any) {
    var url = this.BASE_URL + `author/${JSON.parse(localStorage.getItem('userDetails')!).userId}/books/${bookData.id}`
    return this.httpClient.post(url, bookData);
  }
}
