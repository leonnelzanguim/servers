import {Component, OnInit} from '@angular/core';
import {Server, ServerResponse, ServerUndResponse} from "../../model/server.model";
import {interval, Observable, Subscription} from "rxjs";
import {ServerService} from "../../service/server.service";

@Component({
  selector: 'app-table',
  templateUrl: './table.component.html',
  styleUrls: ['./table.component.css']
})
export class TableComponent implements OnInit {

  serverResponse$!: Observable<ServerResponse[]>;
  private updateSubscription?: Subscription;

  public serverResponseArray: ServerResponse[] = [];
  public newAttribute: ServerUndResponse = {};
  public dataserver: ServerUndResponse[] = [];

  public selectedTimeOut: number = 1;


  constructor(private readonly serverService: ServerService) {
  }

  ngOnInit(): void {

    this.dataserver = [
      {
        "url": "https://postman-echo.com/get",
      }

    ]

    this.updateSubscription = interval(this.selectedTimeOut*60000).subscribe(
      (val) => {
        this.prufen();
      });
  }

  public addFieldValue() {
    this.dataserver.push(this.newAttribute);
    this.newAttribute = {};
  }

  public deleteFieldValue(index: number) {
    this.dataserver.splice(index, 1);
  }

  public prufen(): void {
    this.serverResponse$ = this.serverService.fetchServerCode(this.getData());
    this.serverResponse$.subscribe(code => {
      this.serverResponseArray = code;
         this.updadeDataserverWithCode(this.serverResponseArray);
  });
  }



  onSelected(value:string): void {
    this.selectedTimeOut = +value;
  }

  private getData(): ServerUndResponse[] {
    let currentDataServer: ServerUndResponse[] = [];
    this.dataserver.forEach((el)=>currentDataServer.push(el));
    return currentDataServer;
  }

  private updadeDataserverWithCode(serverResponse: ServerResponse[]): void{
    if(serverResponse.length === this.getData().length){
      for(let i=0; i <= serverResponse.length-1;  i++){
        this.getData()[i].code = serverResponse[i].code;
      }
    }
  }
}
