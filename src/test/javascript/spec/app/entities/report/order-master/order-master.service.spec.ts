/* tslint:disable max-line-length */
import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { of } from 'rxjs';
import { take, map } from 'rxjs/operators';
import { OrderMasterService } from 'app/entities/report/order-master/order-master.service';
import { IOrderMaster, OrderMaster } from 'app/shared/model/report/order-master.model';

describe('Service Tests', () => {
    describe('OrderMaster Service', () => {
        let injector: TestBed;
        let service: OrderMasterService;
        let httpMock: HttpTestingController;
        let elemDefault: IOrderMaster;
        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [HttpClientTestingModule]
            });
            injector = getTestBed();
            service = injector.get(OrderMasterService);
            httpMock = injector.get(HttpTestingController);

            elemDefault = new OrderMaster(
                0,
                'AAAAAAA',
                0,
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                0,
                0,
                0,
                'AAAAAAA',
                'AAAAAAA',
                0,
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                0,
                0,
                'AAAAAAA',
                0,
                0,
                'AAAAAAA',
                'AAAAAAA'
            );
        });

        describe('Service methods', async () => {
            it('should find an element', async () => {
                const returnedFromService = Object.assign({}, elemDefault);
                service
                    .find(123)
                    .pipe(take(1))
                    .subscribe(resp => expect(resp).toMatchObject({ body: elemDefault }));

                const req = httpMock.expectOne({ method: 'GET' });
                req.flush(JSON.stringify(returnedFromService));
            });

            it('should create a OrderMaster', async () => {
                const returnedFromService = Object.assign(
                    {
                        id: 0
                    },
                    elemDefault
                );
                const expected = Object.assign({}, returnedFromService);
                service
                    .create(new OrderMaster(null))
                    .pipe(take(1))
                    .subscribe(resp => expect(resp).toMatchObject({ body: expected }));
                const req = httpMock.expectOne({ method: 'POST' });
                req.flush(JSON.stringify(returnedFromService));
            });

            it('should update a OrderMaster', async () => {
                const returnedFromService = Object.assign(
                    {
                        storeName: 'BBBBBB',
                        storePhone: 1,
                        methodOfOrder: 'BBBBBB',
                        dueDate: 'BBBBBB',
                        dueTime: 'BBBBBB',
                        orderNumber: 'BBBBBB',
                        notes: 'BBBBBB',
                        deliveryCharge: 1,
                        serviceCharge: 1,
                        totalDue: 1,
                        orderStatus: 'BBBBBB',
                        customerId: 'BBBBBB',
                        pincode: 1,
                        houseNoOrBuildingName: 'BBBBBB',
                        roadNameAreaOrStreet: 'BBBBBB',
                        city: 'BBBBBB',
                        state: 'BBBBBB',
                        landmark: 'BBBBBB',
                        name: 'BBBBBB',
                        phone: 1,
                        alternatePhone: 1,
                        addressType: 'BBBBBB',
                        orderFromCustomer: 1,
                        customerOrder: 1,
                        orderPlaceAt: 'BBBBBB',
                        orderAcceptedAt: 'BBBBBB'
                    },
                    elemDefault
                );

                const expected = Object.assign({}, returnedFromService);
                service
                    .update(expected)
                    .pipe(take(1))
                    .subscribe(resp => expect(resp).toMatchObject({ body: expected }));
                const req = httpMock.expectOne({ method: 'PUT' });
                req.flush(JSON.stringify(returnedFromService));
            });

            it('should return a list of OrderMaster', async () => {
                const returnedFromService = Object.assign(
                    {
                        storeName: 'BBBBBB',
                        storePhone: 1,
                        methodOfOrder: 'BBBBBB',
                        dueDate: 'BBBBBB',
                        dueTime: 'BBBBBB',
                        orderNumber: 'BBBBBB',
                        notes: 'BBBBBB',
                        deliveryCharge: 1,
                        serviceCharge: 1,
                        totalDue: 1,
                        orderStatus: 'BBBBBB',
                        customerId: 'BBBBBB',
                        pincode: 1,
                        houseNoOrBuildingName: 'BBBBBB',
                        roadNameAreaOrStreet: 'BBBBBB',
                        city: 'BBBBBB',
                        state: 'BBBBBB',
                        landmark: 'BBBBBB',
                        name: 'BBBBBB',
                        phone: 1,
                        alternatePhone: 1,
                        addressType: 'BBBBBB',
                        orderFromCustomer: 1,
                        customerOrder: 1,
                        orderPlaceAt: 'BBBBBB',
                        orderAcceptedAt: 'BBBBBB'
                    },
                    elemDefault
                );
                const expected = Object.assign({}, returnedFromService);
                service
                    .query(expected)
                    .pipe(
                        take(1),
                        map(resp => resp.body)
                    )
                    .subscribe(body => expect(body).toContainEqual(expected));
                const req = httpMock.expectOne({ method: 'GET' });
                req.flush(JSON.stringify([returnedFromService]));
                httpMock.verify();
            });

            it('should delete a OrderMaster', async () => {
                const rxPromise = service.delete(123).subscribe(resp => expect(resp.ok));

                const req = httpMock.expectOne({ method: 'DELETE' });
                req.flush({ status: 200 });
            });
        });

        afterEach(() => {
            httpMock.verify();
        });
    });
});
