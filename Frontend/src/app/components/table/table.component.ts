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

  public serverArray: Server[] = [];
  public serverResponseArray: ServerResponse[] = [];
  public newAttribute: ServerUndResponse = {};
  //public serverUndResponseArray: ServerUndResponse[] = [];

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
    //this.serverUndResponseArray = this.dataserver;
    //this.dataserver = this.serverUndResponseArray;
  }

  public deleteFieldValue(index: number) {
    this.dataserver.splice(index, 1);
    //this.serverUndResponseArray = this.dataserver;
    //this.dataserver = this.serverUndResponseArray;
  }

  public prufen(): void {
    //this.dataserver =  [];
    //this.dataserver = this.serverUndResponseArray;

    //for(const serverResponse of this.dataserver){
    //  this.serverArray = this.getTheFirstpropretiesFromObject(serverResponse);
    //}
    this.serverResponse$ = this.serverService.fetchServerCode(this.getData());
    this.serverResponse$.subscribe(code => {
      this.serverResponseArray = code;
      /*this.serverResponseArray.forEach((element, index, array) => {
        console.log(this.dataserver[index]);
        console.log(element.code);
        this.dataserver[index].code = element['code'];
        console.log(this.dataserver[index]);
      });*/
         this.updadeDataserverWithCode(this.serverResponseArray);



  });
    //console.log('code: ', this.serverResponseArray);



    //this.serverResponseArray.forEach((element, index, array)=>{
      //console.log(this.dataserver[index]);
      //console.log(element.code);
    //  this.dataserver[index].code = element['code'];
      //console.log(this.dataserver[index]);
   // }
   //                );

    //console.log(this.dataserver);

    //this.serverUndResponseArray = this.dataserver;

    //console.log('test:',  this.serverArray);

    //console.log(this.serverUndResponseArray);

    // extract the first property from the object array ---> the property url

    // send the array object to the backend

    // After control, we become a array of object serverResponse

    // iterate over
  }



  onSelected(value:string): void {
    this.selectedTimeOut = +value;
  }

  public getTheFirstpropretiesFromObject(serverUndResponse: ServerUndResponse) : Server[] {
    let server = {};
    for (const value of Object.values(serverUndResponse)) {
      console.log(`${value}`);
      server = {
        ["url"]: value
      }
      this.serverArray.push(server);
      break;
    }
    return  this.serverArray;
  }

  private getData(): ServerUndResponse[] {
    let currentDataServer: ServerUndResponse[] = [];
    this.dataserver.forEach((el)=>currentDataServer.push(el));
    console.log(this.dataserver.length);
    console.log(currentDataServer.length);
    return currentDataServer;
  }

  private updadeDataserverWithCode(serverResponse: ServerResponse[]): void{
    //console.log('server: ', serverResponse.length);
    //console.log('s: ', this.getData().length);
    //serverResponse.forEach((el)=> console.log('serverResponse: ', el));
    //this.getData().forEach((el)=> console.log('getData: ', el));
    if(serverResponse.length === this.getData().length){
      console.log('all is ok, we got all the data');
      for(let i=0; i <= serverResponse.length-1;  i++){
        this.getData()[i].code = serverResponse[i].code;
      }
    }
  }

}
