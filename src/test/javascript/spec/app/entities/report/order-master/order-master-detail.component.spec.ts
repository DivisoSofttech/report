/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { ReportTestModule } from '../../../../test.module';
import { OrderMasterDetailComponent } from 'app/entities/report/order-master/order-master-detail.component';
import { OrderMaster } from 'app/shared/model/report/order-master.model';

describe('Component Tests', () => {
    describe('OrderMaster Management Detail Component', () => {
        let comp: OrderMasterDetailComponent;
        let fixture: ComponentFixture<OrderMasterDetailComponent>;
        const route = ({ data: of({ orderMaster: new OrderMaster(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ReportTestModule],
                declarations: [OrderMasterDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(OrderMasterDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(OrderMasterDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.orderMaster).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
