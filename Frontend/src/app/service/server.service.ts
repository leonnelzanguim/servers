import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Server, ServerResponse} from "../model/server.model";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class ServerService {

  private readonly serverApiUrl: string = "http://172.19.0.2:8090/api/server";
  //private readonly serverApiUrl: string = "http://localhost:8091/api/server";

  constructor(private readonly httpClient: HttpClient) { }

  fetchServerCode(body: Server[]): Observable<ServerResponse[]> {
    return this.httpClient.post<ServerResponse[]>(`${this.serverApiUrl}`, body);
  }
}
