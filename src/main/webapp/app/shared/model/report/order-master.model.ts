import { IOrderLine } from 'app/shared/model/report/order-line.model';

export interface IOrderMaster {
    id?: number;
    storeName?: string;
    storePhone?: number;
    methodOfOrder?: string;
    dueDate?: string;
    dueTime?: string;
    orderNumber?: string;
    notes?: string;
    deliveryCharge?: number;
    serviceCharge?: number;
    totalDue?: number;
    orderStatus?: string;
    customerId?: string;
    pincode?: number;
    houseNoOrBuildingName?: string;
    roadNameAreaOrStreet?: string;
    city?: string;
    state?: string;
    landmark?: string;
    name?: string;
    phone?: number;
    alternatePhone?: number;
    addressType?: string;
    orderFromCustomer?: number;
    customerOrder?: number;
    orderPlaceAt?: string;
    orderAcceptedAt?: string;
    orderLines?: IOrderLine[];
}

export class OrderMaster implements IOrderMaster {
    constructor(
        public id?: number,
        public storeName?: string,
        public storePhone?: number,
        public methodOfOrder?: string,
        public dueDate?: string,
        public dueTime?: string,
        public orderNumber?: string,
        public notes?: string,
        public deliveryCharge?: number,
        public serviceCharge?: number,
        public totalDue?: number,
        public orderStatus?: string,
        public customerId?: string,
        public pincode?: number,
        public houseNoOrBuildingName?: string,
        public roadNameAreaOrStreet?: string,
        public city?: string,
        public state?: string,
        public landmark?: string,
        public name?: string,
        public phone?: number,
        public alternatePhone?: number,
        public addressType?: string,
        public orderFromCustomer?: number,
        public customerOrder?: number,
        public orderPlaceAt?: string,
        public orderAcceptedAt?: string,
        public orderLines?: IOrderLine[]
    ) {}
}
